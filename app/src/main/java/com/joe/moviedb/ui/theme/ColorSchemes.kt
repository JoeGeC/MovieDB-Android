package com.joe.moviedb.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
internal fun getColorScheme(darkTheme: Boolean  = isSystemInDarkTheme()): ColorScheme = when {
    darkTheme -> DarkColorPalette
    else -> LightColorPalette
}

internal val LightColorPalette = lightColorScheme(
    primary = Color(0xFF04213B),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF03A9F4),
    primaryContainer = Color(0xFF212121),
    onPrimaryContainer = Color(0xFFFFFFFF),
    background = Color(0xFFE0E0E0),
    surface = Color(0xFFFFFFFF),
    onBackground = Color(0xFF212121),
    onSurface = Color(0xFF212121),
    onSurfaceVariant = Color(0xFF2196F3)
)

internal val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF04213B),
    onPrimary = Color(0xFF1E1E1E),
    secondary = Color(0xFF0288D1),
    primaryContainer = Color(0xFF101010),
    onPrimaryContainer = Color(0xFFFFFFFF),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
    onSurfaceVariant = Color(0xFF1976D2),
)