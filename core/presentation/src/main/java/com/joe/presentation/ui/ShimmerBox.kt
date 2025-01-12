package com.joe.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerBox(shimmerInstance: Shimmer? = null) {
    Box(
        Modifier
            .shimmer(shimmerInstance ?: rememberShimmer(ShimmerBounds.View))
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface)
    )
}