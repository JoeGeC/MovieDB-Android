package com.joe.base.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.joe.base.presentation.MediaDetailsModel
import com.joe.presentation.ui.AnimatedScoreCircle
import com.joe.presentation.ui.ErrorScreen
import com.joe.presentation.ui.PosterImage
import com.joe.presentation.ui.ShimmerBox
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.RefreshingState
import com.joe.presentation.viewModels.ViewModelState
import com.joe.details.R as detailsR
import com.joe.presentation.R as presentationR

@Composable
fun DetailsScreenState(state: ViewModelState, refresh: (() -> Unit)? = null) {
    when (state) {
        is ErrorState -> ErrorScreen(onRefresh = refresh)
        is LoadingState -> MovieDetailsLoadingScreen()
        is RefreshingState -> DetailsScreenState(state.previousState)
    }
}

@Composable
fun DetailsSuccessScreen(mediaDetails: MediaDetailsModel, surface: @Composable (ScrollState) -> Unit) {
    val scrollState = rememberScrollState()
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)) {
        BackgroundImage(mediaDetails.backdropPath)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(Modifier.height(100.dp))
            Box {
                surface(scrollState)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 42.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    PosterImage(
                        mediaDetails.posterPath,
                        modifier = Modifier
                            .height(Dimensions.POSTER_IMAGE_HEIGHT.dp)
                            .width(Dimensions.POSTER_IMAGE_WIDTH.dp)
                    )
                    Spacer(Modifier.weight(1f))
                    UserScore(mediaDetails.score)
                }

            }
        }
    }
}

@Composable
private fun BackgroundImage(backgroundImageUrl: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = backgroundImageUrl,
            contentDescription = stringResource(detailsR.string.background_image),
            contentScale = ContentScale.Crop,
            loading = { ShimmerBox() },
            error = {
                Image(
                    painter = painterResource(presentationR.drawable.backdrop_fallback),
                    contentDescription = stringResource(presentationR.string.movie_backdrop_fallback),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        )
    }
}

@Composable
fun UserScore(score: Float?, modifier: Modifier = Modifier) {
    if (score == null) return
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedScoreCircle(
            progress = score,
            size = Dimensions.USER_SCORE_SIZE,
            modifier = modifier
        )
        Text(
            text = stringResource(detailsR.string.user_score_newline),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = 6.dp, top = 4.dp)
        )
    }
}

@Composable
fun MediaTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(top = 12.dp)
    )
}

@Composable
fun ReleaseDate(releaseDate: String) {
    Text(
        text = releaseDate,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 6.dp)
    )
}

@Composable
fun Tagline(tagline: String?) {
    if (tagline.isNullOrEmpty()) return
    Text(
        text = tagline,
        style = MaterialTheme.typography.labelMedium,
        modifier = Modifier.padding(top = 12.dp),
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun Overview(overview: String?) {
    if (overview.isNullOrEmpty()) return
    Column {
        Text(
            text = stringResource(detailsR.string.overview),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = overview,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}
