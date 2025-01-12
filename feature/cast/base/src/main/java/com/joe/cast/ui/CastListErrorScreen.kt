package com.joe.cast.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.joe.cast.R
import com.joe.presentation.ui.ErrorView

@Composable
fun CastListErrorScreen(scrollState: ScrollState, onRefresh: () -> Unit) {
    ExpandableError(stringResource(R.string.cast), scrollState, onRefresh)
}

@Composable
private fun ExpandableError(title: String, scrollState: ScrollState, onRefresh: () -> Unit) {
    AnimatedExpandableHeaderList(
        baseListState = scrollState,
        openOnStart = true,
        expandedHeight = 100.dp
    ) { isExpanded, onToggleExpand, contentAnimatedHeight, iconAnimatedRotation, contentListState ->
        ExpandableContentWithTitle(
            contentAnimatedHeight,
            isExpanded,
            { ClickableTitleWithIcon(title, iconAnimatedRotation, onToggleExpand) },
            {
                ErrorView(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    onRefresh = onRefresh
                )
            }
        )
    }
}