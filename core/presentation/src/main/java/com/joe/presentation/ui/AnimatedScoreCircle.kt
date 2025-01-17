package com.joe.presentation.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import com.joe.presentation.ui.theme.customColorScheme

@Composable
fun AnimatedScoreCircle(
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
            color = getProgressColor(animatedProgress.value),
            strokeWidth = strokeWidth.dp
        )

        ScoreLabel(
            progress,
            animatedProgress,
            numberTextStyle,
            numberTextColor,
            percentageTextStyle,
            percentageTextColor
        )
    }
}

@Composable
private fun getProgressColor(value: Float): Color = when {
    value <= 0.5f -> MaterialTheme.customColorScheme.lowScore
    value <= 0.7 -> MaterialTheme.customColorScheme.mediumScore
    else -> MaterialTheme.customColorScheme.highScore
}

@Composable
private fun ScoreLabel(
    progress: Float,
    animatedProgress: Animatable<Float, AnimationVector1D>,
    numberTextStyle: TextStyle,
    numberTextColor: Color,
    percentageTextStyle: TextStyle,
    percentageTextColor: Color
) {
    Row {
        Text(
            text = if(progress == 0f) "NR" else "${(animatedProgress.value * 100).toInt()}",
            style = numberTextStyle,
            color = numberTextColor
        )
        if(progress > 0f) {
            Text(
                text = "%",
                style = percentageTextStyle,
                color = percentageTextColor,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}