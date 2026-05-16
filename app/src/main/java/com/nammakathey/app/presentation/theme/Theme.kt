package com.nammakathey.app.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColors = lightColorScheme(
    primary = Color(0xFFB43D2A),
    onPrimary = Color.White,
    secondary = Color(0xFF236B5A),
    tertiary = Color(0xFF6E5A2D),
    background = Color(0xFFFAF8F1),
    surface = Color(0xFFFFFCF5),
    surfaceVariant = Color(0xFFECE3D2),
    onBackground = Color(0xFF221B16),
    onSurface = Color(0xFF221B16)
)

@Composable
fun NammaKatheyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColors,
        content = content
    )
}
