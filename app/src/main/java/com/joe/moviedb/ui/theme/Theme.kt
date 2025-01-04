package com.joe.moviedb.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.joe.presentation.ui.theme.LocalCustomColorScheme
import com.joe.presentation.ui.theme.getCustomColorScheme

@Composable
fun MovieAppTheme(
    content: @Composable() () -> Unit
) {
    CompositionLocalProvider(
        LocalCustomColorScheme provides getCustomColorScheme()
    ) {
        MaterialTheme(
            colorScheme = getColorScheme(),
            typography = CustomTypography,
            content = content,
        )
    }
}
