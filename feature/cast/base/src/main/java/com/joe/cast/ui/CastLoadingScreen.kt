package com.joe.cast.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.joe.cast.R
import com.valentinilk.shimmer.shimmer

@Composable
fun CastListLoadingScreen(baseListState: ScrollState) {
    AnimatedExpandableHeaderList(
        baseListState = baseListState,
        openOnStart = true,
        expandedHeight = 380.dp
    ) { isExpanded, onToggleExpand, contentAnimatedHeight, iconAnimatedRotation, contentListState ->
        ExpandableContentWithTitle(
            contentAnimatedHeight,
            isExpanded,
            { ClickableTitleWithIcon(stringResource(R.string.cast), iconAnimatedRotation, onToggleExpand) },
            { ListShimmer() }
        )
    }
}

@Composable
private fun ListShimmer() {
    LazyRow(
        Modifier.fillMaxWidth().shimmer(),
        contentPadding = PaddingValues(horizontal = 26.dp),
    ) {
        items(6) {
            Box(
                Modifier
                    .padding(horizontal = 8.dp)
                    .height(380.dp)
                    .width(Dimensions.PERSON_WIDTH.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.colorScheme.onSurface)
            )
        }
    }
}
