package com.joe.cast.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedExpandableHeaderList(
    baseListState: ScrollState? = null,
    openOnStart: Boolean,
    expandedHeight: Dp = 380.dp,
    content: @Composable (
        isExpanded: Boolean,
        onClick: () -> Unit,
        listVisibility: Dp,
        rotationAngle: Float,
        personListState: LazyListState
    ) -> Unit
) {
    val expandableListState = rememberLazyListState()
    var isExpanded by remember { mutableStateOf(openOnStart) }
    var userHasExpanded by remember { mutableStateOf(false) }

    val listVisibility by animateDpAsState(
        targetValue = if (isExpanded) expandedHeight else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = "Expanding list"
    )

    val iconAnimationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "Rotating arrow"
    )

    content(
        isExpanded,
        {
            isExpanded = !isExpanded
            userHasExpanded = true
        },
        listVisibility,
        iconAnimationAngle,
        expandableListState
    )

    LaunchedEffect(isExpanded) {
        if (isExpanded && userHasExpanded) {
            baseListState?.animateScrollBy(800f)
        }
    }
}