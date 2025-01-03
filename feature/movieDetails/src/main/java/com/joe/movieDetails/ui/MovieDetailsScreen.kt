package com.joe.movieDetails.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.SubcomposeAsyncImage
import com.joe.feature.R as featureR
import com.joe.presentation.R as presentationR
import com.joe.movieDetails.presentation.MovieDetailsSuccessState
import com.joe.movieDetails.presentation.MovieDetailsViewModel
import com.joe.presentation.model.MediaDetailsModel
import com.joe.presentation.ui.AnimatedCircularProgressBar
import com.joe.presentation.ui.ErrorScreen
import com.joe.presentation.ui.ShimmerBox
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.RefreshingState
import com.joe.presentation.viewModels.ViewModelState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    movieId: Int?,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(movieId) { viewModel.init(movieId) }
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        println("State: $state")
        PullToRefreshBox(
            isRefreshing = state is RefreshingState,
            onRefresh = viewModel::refresh
        ) {
            Box(Modifier.verticalScroll(rememberScrollState())){
                ScreenState(state)
            }
        }
    }
}

@Composable
private fun ScreenState(state: ViewModelState) {
    when (state) {
        is MovieDetailsSuccessState -> MovieDetailsSuccessScreen(state.movieDetails)
        is ErrorState -> ErrorScreen()
        is LoadingState -> MovieDetailsLoadingScreen()
        is RefreshingState -> { ScreenState(state.previousState) }
    }
}

@Composable
fun MovieDetailsSuccessScreen(movieDetails: MediaDetailsModel) {
//    val scrollState = rememberScrollState()
    Box(Modifier.fillMaxSize()) {
        BackgroundImage(movieDetails.backdropPath)
        Box(
            modifier = Modifier
                .padding(top = 100.dp)
//                .verticalScroll(scrollState)
        ) {
            MovieDetailsSurface(movieDetails)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                PosterImage(movieDetails.posterPath)
                Spacer(Modifier.weight(1f))
                UserScore(movieDetails.score)
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
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            loading = { ShimmerBox() },
            error = { painterResource(presentationR.drawable.backdrop_fallback) }
        )
    }
}

@Composable
private fun MovieDetailsSurface(movieDetails: MediaDetailsModel) {
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
                .padding(horizontal = 28.dp),
        ) {

            MovieTitle(movieDetails.title)
            ReleaseDate(movieDetails.releaseDate)
            Tagline(movieDetails.tagline)
            Overview(movieDetails.overview)
        }
    }
}

@Composable
private fun PosterImage(posterImageUrl: String?) {
    Box(
        modifier = Modifier
            .height(Dimensions.POSTER_IMAGE_HEIGHT.dp)
            .width(Dimensions.POSTER_IMAGE_WIDTH.dp)
            .clip(RoundedCornerShape(8))
    ) {
        SubcomposeAsyncImage(
            model = posterImageUrl,
            contentDescription = "Movie Poster",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            loading = { ShimmerBox() },
            error = { painterResource(presentationR.drawable.poster_fallback) }
        )
    }
}

@Composable
private fun MovieTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(top = 12.dp)
    )
}

@Composable
private fun ReleaseDate(releaseDate: String) {
    Text(
        text = releaseDate,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 6.dp)
    )
}

@Composable
private fun Tagline(tagline: String) {
    Text(
        text = tagline,
        style = MaterialTheme.typography.labelMedium,
        modifier = Modifier.padding(top = 12.dp),
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun Overview(overview: String) {
    Text(
        text = stringResource(featureR.string.overview),
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 12.dp)
    )

    Text(
        text = overview,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 6.dp)
    )
}

@Composable
fun UserScore(score: Float?, modifier: Modifier = Modifier) {
    if (score == null) return
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedCircularProgressBar(
            progress = score,
            size = Dimensions.USER_SCORE_SIZE,
            modifier = modifier
        )
        Text(
            text = stringResource(featureR.string.user_score_newline),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = 6.dp, top = 4.dp)
        )
    }
}
