package com.joe.movieDetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun MovieDetailsLoadingScreen() {
    Box(
        modifier = Modifier
            .padding(top = 100.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        DetailsSurface()
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
                    .height(Dimensions.POSTER_IMAGE_HEIGHT.dp)
                    .width(Dimensions.POSTER_IMAGE_WIDTH.dp)
                    .background(MaterialTheme.colorScheme.onSurface)
            )
            Spacer(Modifier.weight(1f))
            Box(
                Modifier
                    .size(Dimensions.USER_SCORE_SIZE.dp)
                    .background(color = MaterialTheme.colorScheme.onSurface, shape = CircleShape)
            )
            Box(Modifier.width(40.dp))
        }
    }
}

@Composable
private fun DetailsSurface() {
    Surface(
        modifier = Modifier
            .padding(top = 140.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, bottom = 16.dp)
                .padding(horizontal = 28.dp)
                .shimmer()
        ) {
            Box(
                Modifier
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(16))
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(MaterialTheme.colorScheme.onSurface)
            )
            Box(
                Modifier
                    .padding(top = 6.dp)
                    .clip(RoundedCornerShape(22))
                    .width(100.dp)
                    .height(32.dp)
                    .background(MaterialTheme.colorScheme.onSurface)
            )
            Box(
                Modifier
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(20))
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(MaterialTheme.colorScheme.onSurface)
            )
            Box(
                Modifier
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(4))
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.onSurface)
            )
        }
    }
}