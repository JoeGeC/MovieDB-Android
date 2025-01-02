package com.joe.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.valentinilk.shimmer.shimmer

@Composable
fun ImageShimmer() {
    Box(
        Modifier
            .shimmer()
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface)
    )
}