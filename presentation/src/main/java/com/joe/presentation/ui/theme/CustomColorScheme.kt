package com.joe.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorScheme(
    val lowScore: Color = Color.Unspecified,
    val mediumScore: Color = Color.Unspecified,
    val highScore: Color = Color.Unspecified
)

val LightCustomColorPalette = CustomColorScheme(
    lowScore = Color(0xFFC51F57),
    mediumScore = Color(0xFFBDBF2C),
    highScore = Color(0xFF1AA000)
)

val DarkCustomColorPalette = CustomColorScheme(
    lowScore = Color(0xFFC51F57),
    mediumScore = Color(0xFFBDBF2C),
    highScore = Color(0xFF1A7749)
)

val LocalCustomColorScheme = staticCompositionLocalOf { CustomColorScheme() }

@Composable
fun getCustomColorScheme(darkTheme: Boolean = isSystemInDarkTheme()): CustomColorScheme =
    when {
        darkTheme -> DarkCustomColorPalette
        else -> LightCustomColorPalette
    }

val MaterialTheme.customColorScheme: CustomColorScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColorScheme.current