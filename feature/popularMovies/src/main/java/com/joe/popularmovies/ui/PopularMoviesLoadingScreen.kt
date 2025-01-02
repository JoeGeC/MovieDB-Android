package com.joe.popularmovies.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun PopularMoviesLoadingScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 42.dp)
            .shimmer(),
        verticalAlignment = Alignment.Bottom
    ) {
        Box(
            Modifier
                .clip(RoundedCornerShape(8))
                .height(40.dp)
                .width(30.dp)
                .background(MaterialTheme.colorScheme.onSurface)
        )
        Spacer(Modifier.weight(1f))
        Box(
            Modifier
                .size(40.dp)
                .background(color = MaterialTheme.colorScheme.onSurface, shape = CircleShape)
        )
        Box(Modifier.width(40.dp))
    }
}