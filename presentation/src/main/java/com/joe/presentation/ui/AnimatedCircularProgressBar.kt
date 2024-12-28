package com.joe.presentation.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedCircularProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Float = 75f,
    strokeWidth: Float = 6f,
    numberTextStyle: TextStyle = MaterialTheme.typography.headlineMedium,
    percentageTextStyle: TextStyle = MaterialTheme.typography.labelSmall,
    numberTextColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    percentageTextColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
) {
    val animatedProgress = remember { Animatable(0f) }
    val progressBarSize = remember { size * 0.9 }

    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress / 10f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size.dp)
            .background(color = backgroundColor, shape = CircleShape)
    ) {

        CircularProgressIndicator(
            progress = { animatedProgress.value },
            modifier = Modifier.size(progressBarSize.dp),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = strokeWidth.dp
        )

        ScoreLabel(
            animatedProgress,
            numberTextStyle,
            numberTextColor,
            percentageTextStyle,
            percentageTextColor
        )
    }
}

@Composable
private fun ScoreLabel(
    progress: Animatable<Float, AnimationVector1D>,
    numberTextStyle: TextStyle,
    numberTextColor: Color,
    percentageTextStyle: TextStyle,
    percentageTextColor: Color
) {
    Row {
        Text(
            text = "${(progress.value * 100).toInt()}",
            style = numberTextStyle,
            color = numberTextColor
        )
        Text(
            text = "%",
            style = percentageTextStyle,
            color = percentageTextColor,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}