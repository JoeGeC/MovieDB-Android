package com.joe.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.SubcomposeAsyncImage
import com.joe.presentation.R

@Composable
fun PosterImage(posterImageUrl: String?, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8))
    ) {
        SubcomposeAsyncImage(
            model = posterImageUrl,
            contentDescription = stringResource(R.string.movie_poster),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            loading = { ShimmerBox() },
            error = {
                Image(
                    painter = painterResource(R.drawable.poster_fallback),
                    contentDescription = stringResource(R.string.movie_poster_fallback)
                )
            }
        )
    }
}