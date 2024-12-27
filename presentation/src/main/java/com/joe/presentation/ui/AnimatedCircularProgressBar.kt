package com.joe.presentation.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedCircularProgressBar(progress: Float, modifier: Modifier = Modifier) {
    val animatedProgress = remember { Animatable(0f) }
    val strokeColor = MaterialTheme.colorScheme.onSurfaceVariant
    val progressColor = MaterialTheme.colorScheme.primary
    val backgroundColor = MaterialTheme.colorScheme.primaryContainer

    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress / 10f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(80.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 6.dp.toPx()
            val radius = size.minDimension / 2 - strokeWidth / 2
            val innerRadius = radius - 6.dp.toPx()
            val center = size.center

            drawCircle(
                color = backgroundColor,
                radius = radius,
            )

            drawCircle(
                color = strokeColor,
                radius = innerRadius,
                center = center,
                style = Stroke(width = strokeWidth)
            )

            drawArc(
                color = progressColor,
                startAngle = -90f,
                sweepAngle = 360 * animatedProgress.value,
                useCenter = false,
                style = Stroke(width = strokeWidth, pathEffect = null),
                size = Size(innerRadius * 2, innerRadius * 2),
                topLeft = Offset(
                    (size.width - innerRadius * 2) / 2,
                    (size.height - innerRadius * 2) / 2
                )
            )
        }

        ScoreLabel(animatedProgress)
    }
}

@Composable
private fun ScoreLabel(progress: Animatable<Float, AnimationVector1D>) {
    Row{
        Text(
            text = "${(progress.value * 100).toInt()}",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = "%",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}