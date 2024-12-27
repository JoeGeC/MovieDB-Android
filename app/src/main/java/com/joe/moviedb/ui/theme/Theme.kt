package com.joe.moviedb.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun MovieAppTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colorScheme = getColorScheme(),
        typography = CustomTypography,
        content = content,
    )
}
