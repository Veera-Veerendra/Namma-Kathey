package com.nammakathey.app.domain

enum class Language {
    Kannada,
    English
}

data class LocalText(
    val kannada: String,
    val english: String
) {
    fun value(language: Language): String = when (language) {
        Language.Kannada -> kannada
        Language.English -> english
    }
}

data class District(
    val id: String,
    val name: LocalText,
    val region: String,
    val heroIds: List<String>
)

data class Hero(
    val id: String,
    val districtId: String,
    val name: LocalText,
    val title: LocalText,
    val years: String,
    val imageUrl: String, // Changed from portraitSeed to imageUrl
    val summary: LocalText,
    val interestingFacts: List<LocalText>, // Added field
    val pages: List<StoryPage>,
    val quiz: List<Question>,
    val badge: Badge,
    val memorial: Memorial
)

data class StoryPage(
    val heading: LocalText,
    val body: LocalText
)

data class Question(
    val prompt: LocalText,
    val options: List<LocalText>,
    val correctIndex: Int,
    val explanation: LocalText
)

data class Badge(
    val id: String,
    val name: LocalText,
    val description: LocalText
)

data class Memorial(
    val place: LocalText,
    val distanceHint: String
)

data class QuizResult(
    val correct: Int,
    val total: Int
) {
    // Adjusted passing criteria: 2 out of 3 is usually enough for children
    val passed: Boolean get() = if (total > 0) (correct.toFloat() / total.toFloat()) >= 0.66f else false
}
