package com.nammakathey.app

import android.app.Application
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nammakathey.app.data.NammaKatheyRepository
import com.nammakathey.app.domain.Language
import com.nammakathey.app.domain.QuizResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Locale

data class NammaKatheyState(
    val user: String? = null,
    val language: Language = Language.English,
    val selectedDistrictId: String? = null,
    val selectedHeroId: String? = null,
    val storyPage: Int = 0,
    val quizAnswers: Map<Int, Int> = emptyMap(),
    val lastQuizResult: QuizResult? = null,
    val earnedBadges: Set<String> = emptySet(),
    val isLoading: Boolean = false
)

class MainViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {
    val repository = NammaKatheyRepository()
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private var tts: TextToSpeech? = null
    private var ttsReady = false

    private val _state = MutableStateFlow(NammaKatheyState())
    val state: StateFlow<NammaKatheyState> = _state

    init {
        try {
            tts = TextToSpeech(application, this)
        } catch (e: Exception) {
            Log.e("MainViewModel", "TTS Init failed", e)
        }
        signInAnonymously()
    }

    private fun signInAnonymously() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val user = auth.currentUser ?: auth.signInAnonymously().await().user
                _state.update { it.copy(user = user?.uid) }
                loadUserData(user?.uid)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Auth failed", e)
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun loadUserData(uid: String?) {
        if (uid == null) return
        viewModelScope.launch {
            try {
                val doc = db.collection("users").document(uid).get().await()
                if (doc.exists()) {
                    val badgeList = doc.get("earnedBadges") as? List<*>
                    val badgesFromCloud = badgeList?.filterIsInstance<String>()?.toSet() ?: emptySet()
                    
                    // Merge cloud badges with any badges earned during this session before sync finished
                    _state.update { it.copy(
                        earnedBadges = it.earnedBadges + badgesFromCloud,
                        isLoading = false
                    ) }
                    Log.d("MainViewModel", "Loaded and merged badges: ${_state.value.earnedBadges}")
                } else {
                    _state.update { it.copy(isLoading = false) }
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Firestore load failed", e)
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    fun setLanguage(language: Language) {
        _state.update { it.copy(language = language) }
    }

    fun selectDistrict(districtId: String) {
        val firstHero = repository.heroesForDistrict(districtId).firstOrNull()?.id
        _state.update {
            it.copy(
                selectedDistrictId = districtId,
                selectedHeroId = firstHero,
                storyPage = 0,
                quizAnswers = emptyMap(),
                lastQuizResult = null
            )
        }
    }

    fun selectHero(heroId: String) {
        _state.update {
            it.copy(
                selectedHeroId = heroId,
                storyPage = 0,
                quizAnswers = emptyMap(),
                lastQuizResult = null
            )
        }
    }

    fun setStoryPage(page: Int) {
        _state.update { it.copy(storyPage = page.coerceAtLeast(0)) }
    }

    fun speakCurrentPage() {
        if (!ttsReady) {
            Toast.makeText(getApplication(), "Voice engine not ready", Toast.LENGTH_SHORT).show()
            return
        }
        val s = _state.value
        val hero = s.selectedHeroId?.let(repository::hero) ?: return
        val page = hero.pages.getOrNull(s.storyPage) ?: return
        val text = page.body.value(s.language)
        
        viewModelScope.launch {
            try {
                val locale = if (s.language == Language.Kannada) Locale("kn", "IN") else Locale.US
                val result = tts?.setLanguage(locale)
                
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("MainViewModel", "Language $locale not supported. Trying generic kn.")
                    if (s.language == Language.Kannada) {
                        val secondTry = tts?.setLanguage(Locale("kn"))
                        if (secondTry == TextToSpeech.LANG_MISSING_DATA || secondTry == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Toast.makeText(getApplication(), "Kannada voice not found. Please install Google TTS Kannada data.", Toast.LENGTH_LONG).show()
                            return@launch
                        }
                    }
                }
                
                tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "page_audio")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Speech failed", e)
            }
        }
    }

    fun answer(questionIndex: Int, optionIndex: Int) {
        _state.update {
            it.copy(
                quizAnswers = it.quizAnswers + (questionIndex to optionIndex),
                lastQuizResult = null
            )
        }
    }

    fun submitQuiz() {
        val current = _state.value
        val hero = current.selectedHeroId?.let(repository::hero) ?: return
        val correct = hero.quiz.foldIndexed(0) { index, acc, question ->
            if (current.quizAnswers[index] == question.correctIndex) acc + 1 else acc
        }
        val result = QuizResult(correct, hero.quiz.size)

        Log.d("MainViewModel", "Quiz for ${hero.id}: $correct/${hero.quiz.size}. Passed: ${result.passed}")

        if (result.passed) {
            saveBadgeToFirebase(hero.badge.id)
        }

        _state.update { it.copy(lastQuizResult = result) }
    }

    private fun saveBadgeToFirebase(badgeId: String) {
        // Optimistic UI Update: Add badge locally first so the user sees it immediately
        _state.update { it.copy(earnedBadges = it.earnedBadges + badgeId) }

        val uid = auth.currentUser?.uid ?: run {
            Log.w("MainViewModel", "Cannot sync to cloud: No user UID yet")
            return
        }

        viewModelScope.launch {
            try {
                // Ensure we have the latest set from state
                val updatedBadgesList = _state.value.earnedBadges.toList()
                db.collection("users").document(uid)
                    .set(mapOf("earnedBadges" to updatedBadgesList))
                    .await()
                Log.d("MainViewModel", "Badge $badgeId synced to Firebase")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Failed to sync badge to Firestore", e)
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            ttsReady = true
            Log.d("MainViewModel", "TTS Engine Initialized")
        } else {
            Log.e("MainViewModel", "TTS Init failed with status: $status")
        }
    }

    override fun onCleared() {
        super.onCleared()
        try {
            tts?.stop()
            tts?.shutdown()
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error shutting down TTS", e)
        }
    }
}
