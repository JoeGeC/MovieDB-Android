package com.joe.popular.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.setSingletonImageLoaderFactory
import com.joe.popular.presentation.model.MediaListItemModel
import com.joe.presentation.R
import com.joe.presentation.ui.AnimatedCircularProgressBar
import com.joe.presentation.ui.ShimmerBox

@Composable
fun MediaListItem(movie: MediaListItemModel, onClick: (() -> Unit)? = null) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = { onClick?.invoke() })
    ) {
        Column {
            Box(contentAlignment = Alignment.BottomStart) {
                PosterImage(movie.posterPath)
                AnimatedCircularProgressBar(
                    movie.score,
                    size = 50f,
                    numberTextStyle = MaterialTheme.typography.titleSmall,
                    percentageTextStyle = MaterialTheme.typography.labelSmall,
                    strokeWidth = 4f,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .offset(y = 20.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 10.dp)
            ) {
                Text(movie.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(movie.releaseDate, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}


@Composable
private fun PosterImage(posterImageUrl: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.667f)
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