package com.nammakathey.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.nammakathey.app.domain.Hero
import com.nammakathey.app.domain.Language
import com.nammakathey.app.domain.LocalText
import com.nammakathey.app.presentation.theme.NammaKatheyTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NammaKatheyTheme {
                NammaKatheyApp(mainViewModel)
            }
        }
    }
}

private enum class Route(val id: String, val label: String, val icon: ImageVector) {
    Home("home", "Map", Icons.Default.Map),
    Heroes("heroes", "Heroes", Icons.Default.Explore),
    Story("story", "Story", Icons.Default.Book),
    Quiz("quiz", "Quiz", Icons.Default.Info),
    Badges("badges", "Badges", Icons.Default.Star),
    Finder("finder", "Finder", Icons.Default.LocationOn)
}

@Composable
private fun NammaKatheyApp(viewModel: MainViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()
    val selectedHero = state.selectedHeroId?.let(viewModel.repository::hero)

    Scaffold(
        bottomBar = { BottomNav(navController) }
    ) { padding ->
        Box(Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = Route.Home.id,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(padding)
            ) {
                composable(Route.Home.id) {
                    HomeScreen(
                        state = state,
                        viewModel = viewModel,
                        onDistrict = {
                            viewModel.selectDistrict(it)
                            navController.navigate(Route.Heroes.id)
                        }
                    )
                }
                composable(Route.Heroes.id) {
                    HeroesScreen(
                        state = state,
                        viewModel = viewModel,
                        onRead = { navController.navigate(Route.Story.id) },
                        onQuiz = { navController.navigate(Route.Quiz.id) }
                    )
                }
                composable(Route.Story.id) {
                    StoryScreen(
                        state = state,
                        hero = selectedHero,
                        onPageChanged = viewModel::setStoryPage,
                        onSpeak = viewModel::speakCurrentPage,
                        onQuiz = { navController.navigate(Route.Quiz.id) }
                    )
                }
                composable(Route.Quiz.id) {
                    QuizScreen(
                        state = state,
                        hero = selectedHero,
                        onAnswer = viewModel::answer,
                        onSubmit = viewModel::submitQuiz,
                        onBadges = { navController.navigate(Route.Badges.id) }
                    )
                }
                composable(Route.Badges.id) {
                    BadgesScreen(state = state, viewModel = viewModel)
                }
                composable(Route.Finder.id) {
                    FinderScreen(state = state, hero = selectedHero)
                }
            }

            if (state.isLoading && state.user == null) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f)),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 4.dp
                    ) {
                        Row(Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp))
                            Spacer(Modifier.width(16.dp))
                            Text("Loading Pride...", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomNav(navController: NavHostController) {
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route

    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        Route.entries.forEach { route ->
            NavigationBarItem(
                selected = currentRoute == route.id,
                onClick = {
                    navController.navigate(route.id) {
                        launchSingleTop = true
                        popUpTo(Route.Home.id) { saveState = true }
                        restoreState = true
                    }
                },
                icon = { Icon(route.icon, contentDescription = route.label) },
                label = { Text(route.label, maxLines = 1, overflow = TextOverflow.Ellipsis) }
            )
        }
    }
}

@Composable
private fun HomeScreen(
    state: NammaKatheyState,
    viewModel: MainViewModel,
    onDistrict: (String) -> Unit
) {
    Screen {
        Header(
            title = LocalText("ನಮ್ಮ ಕಥೆ", "Namma Kathey").value(state.language),
            subtitle = LocalText(
                "ಕರ್ನಾಟಕದ ನಾಯಕರ ಕಥೆಗಳು, ಪ್ರಶ್ನೋತ್ತರಗಳು ಮತ್ತು ನೆನಪು ತಾಣಗಳು.",
                "Stories, quizzes, and memory places from Karnataka's local history."
            ).value(state.language)
        )
        LanguageToggle(state.language, viewModel::setLanguage)

        SectionTitle(
            LocalText("ಜಿಲ್ಲೆ ಆಯ್ಕೆಮಾಡಿ", "Choose a district").value(state.language),
            LocalText("ಪ್ರತಿ ಜಿಲ್ಲೆಯಲ್ಲಿ ಒಂದು ಕಥೆಯಿಂದ ಆರಂಭಿಸಿ.", "Start with one story from each district.").value(state.language)
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 18.dp)
        ) {
            items(viewModel.repository.districts) { district ->
                OutlinedCard(
                    onClick = { onDistrict(district.id) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Map, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(district.name.value(state.language), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text(district.region, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
                        }
                        Text("${district.heroIds.size}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroesScreen(
    state: NammaKatheyState,
    viewModel: MainViewModel,
    onRead: () -> Unit,
    onQuiz: () -> Unit
) {
    val districtId = state.selectedDistrictId ?: viewModel.repository.districts.first().id
    val district = viewModel.repository.district(districtId)
    val heroes = viewModel.repository.heroesForDistrict(districtId)
    val selectedHero = state.selectedHeroId?.let(viewModel.repository::hero) ?: heroes.firstOrNull()

    Screen {
        Header(
            title = district?.name?.value(state.language) ?: "Heroes",
            subtitle = LocalText("ಕಥೆ ಆರಿಸಿ, ಓದಿ, ನಂತರ ಬ್ಯಾಡ್ಜ್ ಗಳಿಸಿ.", "Pick a hero, read the story, then earn a badge.").value(state.language)
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            items(viewModel.repository.districts) { item ->
                FilterChip(
                    selected = item.id == districtId,
                    onClick = { viewModel.selectDistrict(item.id) },
                    label = { Text(item.name.value(state.language), maxLines = 1) }
                )
            }
        }
        Spacer(Modifier.height(4.dp))
        LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(heroes) { hero ->
                HeroPanel(
                    hero = hero,
                    language = state.language,
                    selected = hero.id == selectedHero?.id,
                    earned = state.earnedBadges.contains(hero.badge.id),
                    onSelect = { viewModel.selectHero(hero.id) },
                    onRead = onRead,
                    onQuiz = onQuiz
                )
            }
        }
    }
}

@Composable
private fun HeroPanel(
    hero: Hero,
    language: Language,
    selected: Boolean,
    earned: Boolean,
    onSelect: () -> Unit,
    onRead: () -> Unit,
    onQuiz: () -> Unit
) {
    OutlinedCard(
        onClick = onSelect,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = hero.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(hero.name.value(language), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(hero.title.value(language), color = MaterialTheme.colorScheme.secondary)
                    Text(hero.years, style = MaterialTheme.typography.bodySmall)
                }
                if (earned) {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Badge earned", tint = MaterialTheme.colorScheme.primary)
                }
            }
            Text(hero.summary.value(language), style = MaterialTheme.typography.bodyLarge)
            
            // Interesting Facts Section
            if (hero.interestingFacts.isNotEmpty()) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Lightbulb, contentDescription = null, tint = Color(0xFFF0C55A), modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = if (language == Language.Kannada) "ಆಸಕ್ತಿದಾಯಕ ಸಂಗತಿಗಳು" else "Interesting Facts",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    hero.interestingFacts.forEach { fact ->
                        Text("• ${fact.value(language)}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(onClick = onRead) {
                    Icon(Icons.Default.Book, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(LocalText("ಓದಿ", "Read").value(language))
                }
                OutlinedButton(onClick = onQuiz) {
                    Icon(Icons.Default.Info, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(LocalText("ಪ್ರಶ್ನೆ", "Quiz").value(language))
                }
            }
        }
    }
}

@Composable
private fun StoryScreen(
    state: NammaKatheyState,
    hero: Hero?,
    onPageChanged: (Int) -> Unit,
    onSpeak: () -> Unit,
    onQuiz: () -> Unit
) {
    if (hero == null) {
        EmptyScreen("Choose a hero first.")
        return
    }

    key(hero.id) {
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { hero.pages.size })
        val scope = rememberCoroutineScope()

        LaunchedEffect(pagerState.currentPage) {
            onPageChanged(pagerState.currentPage)
        }

        Screen {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Header(hero.name.value(state.language), hero.title.value(state.language))
                IconButton(onClick = onSpeak) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "Speak", tint = MaterialTheme.colorScheme.primary)
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { pageIndex ->
                val page = hero.pages[pageIndex]
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            model = hero.imageUrl,
                            contentDescription = hero.name.english,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Text(page.heading.value(state.language), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                        Text(page.body.value(state.language), style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("${pagerState.currentPage + 1} / ${hero.pages.size}", style = MaterialTheme.typography.labelLarge)
                
                if (pagerState.currentPage == hero.pages.size - 1) {
                    Button(onClick = onQuiz) {
                        Text(LocalText("ಪ್ರಶ್ನೋತ್ತರ", "Start Quiz").value(state.language))
                    }
                } else {
                    OutlinedButton(onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } }) {
                        Text(LocalText("ಮುಂದೆ", "Next").value(state.language))
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizScreen(
    state: NammaKatheyState,
    hero: Hero?,
    onAnswer: (Int, Int) -> Unit,
    onSubmit: () -> Unit,
    onBadges: () -> Unit
) {
    if (hero == null) {
        EmptyScreen("Choose a hero first.")
        return
    }
    val canSubmit = state.quizAnswers.size == hero.quiz.size

    Screen {
        Header(
            LocalText("ಪ್ರಶ್ನೋತ್ತರ", "Quiz").value(state.language),
            hero.name.value(state.language)
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
            items(hero.quiz.size) { index ->
                val question = hero.quiz[index]
                OutlinedCard(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp)) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(question.prompt.value(state.language), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        question.options.forEachIndexed { optionIndex, option ->
                            FilterChip(
                                selected = state.quizAnswers[index] == optionIndex,
                                onClick = { onAnswer(index, optionIndex) },
                                label = { Text(option.value(state.language)) }
                            )
                        }
                    }
                }
            }
        }

        state.lastQuizResult?.let { result ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = if (result.passed) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer,
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("${result.correct}/${result.total}", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text(
                        if (result.passed) hero.badge.description.value(state.language)
                        else LocalText("ಮತ್ತೊಮ್ಮೆ ಪ್ರಯತ್ನಿಸಿ.", "Try again to earn the badge.").value(state.language),
                        textAlign = TextAlign.Center
                    )
                    if (result.passed) {
                        ElevatedButton(onClick = onBadges) {
                            Icon(Icons.Default.Star, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text(LocalText("ಬ್ಯಾಡ್ಜ್ ನೋಡಿ", "View Badge").value(state.language))
                        }
                    }
                }
            }
        } ?: run {
            Button(
                enabled = canSubmit,
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(LocalText("ಉತ್ತರ ಸಲ್ಲಿಸಿ", "Submit Answers").value(state.language))
            }
        }
    }
}

@Composable
private fun BadgesScreen(state: NammaKatheyState, viewModel: MainViewModel) {
    val heroes = viewModel.repository.heroes
    val earnedBadges = state.earnedBadges

    Screen {
        Header(
            title = LocalText("ನನ್ನ ಬ್ಯಾಡ್ಜ್‌ಗಳು", "My Badges").value(state.language),
            subtitle = LocalText("ಗಳಿಸಿದ ಹೆಮ್ಮೆಯ ಸಂಕೇತಗಳು.", "Badges you've earned through knowledge.").value(state.language)
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(heroes, key = { it.id }) { hero ->
                val earned = earnedBadges.contains(hero.badge.id)
                OutlinedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(
                        width = if (earned) 2.dp else 1.dp,
                        color = if (earned) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (earned) Icons.Default.Star else Icons.Default.Lock,
                            contentDescription = null,
                            tint = if (earned) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(Modifier.width(16.dp))
                        Column(Modifier.weight(1f)) {
                            Text(
                                text = hero.badge.name.value(state.language),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (earned) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = hero.name.value(state.language),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            if (earned) {
                                Text(
                                    text = hero.badge.description.value(state.language),
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FinderScreen(state: NammaKatheyState, hero: Hero?) {
    val context = LocalContext.current
    Screen {
        Header(
            LocalText("ಸ್ಮಾರಕ ಹುಡುಕಿ", "Statue Finder").value(state.language),
            LocalText("ಆಯ್ದ ನಾಯಕನ ನೆನಪು ತಾಣವನ್ನು ನೋಡಿ.", "Visit the memorial of your hero.").value(state.language)
        )
        if (hero == null) {
            EmptyScreen("Choose a hero first.")
        } else {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(32.dp))
                        Spacer(Modifier.width(10.dp))
                        Text(hero.memorial.place.value(state.language), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }
                    Text(hero.memorial.distanceHint, style = MaterialTheme.typography.bodyLarge)
                    
                    Button(
                        onClick = {
                            val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(hero.memorial.place.english)}")
                            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                            mapIntent.setPackage("com.google.android.apps.maps")
                            context.startActivity(mapIntent)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(LocalText("ನಕ್ಷೆಯಲ್ಲಿ ನೋಡಿ", "Open in Maps").value(state.language))
                    }
                }
            }
        }
    }
}

@Composable
private fun Screen(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        content = content
    )
}

@Composable
private fun Header(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold)
        Text(subtitle, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.secondary)
    }
}

@Composable
private fun SectionTitle(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(subtitle, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun LanguageToggle(language: Language, onLanguage: (Language) -> Unit) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        FilterChip(
            selected = language == Language.English,
            onClick = { onLanguage(Language.English) },
            leadingIcon = { Icon(Icons.Default.Translate, contentDescription = null, modifier = Modifier.size(18.dp)) },
            label = { Text("English") }
        )
        FilterChip(
            selected = language == Language.Kannada,
            onClick = { onLanguage(Language.Kannada) },
            leadingIcon = { Icon(Icons.Default.Translate, contentDescription = null, modifier = Modifier.size(18.dp)) },
            label = { Text("ಕನ್ನಡ") }
        )
    }
}

@Composable
private fun EmptyScreen(message: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(message, style = MaterialTheme.typography.bodyLarge)
    }
}
