package com.joe.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.SubcomposeAsyncImage
import com.joe.presentation.R
import com.valentinilk.shimmer.Shimmer

@Composable
fun PosterImage(posterImageUrl: String?, shimmerInstance: Shimmer? = null, modifier: Modifier = Modifier) {
    Box(modifier = modifier.clip(RoundedCornerShape(8))) {
        SubcomposeAsyncImage(
            model = posterImageUrl,
            contentDescription = stringResource(R.string.movie_poster),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            loading = { ShimmerBox(shimmerInstance) },
            error = {
                Image(
                    painter = painterResource(R.drawable.poster_fallback),
                    contentDescription = stringResource(R.string.movie_poster_fallback)
                )
            }
        )
    }
}