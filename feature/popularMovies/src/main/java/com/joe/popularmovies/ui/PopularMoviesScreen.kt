package com.joe.popularmovies.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.SubcomposeAsyncImage
import com.joe.presentation.R
import com.joe.popularmovies.presentation.PopularMoviesLoadingMoreState
import com.joe.popularmovies.presentation.PopularMoviesSuccessState
import com.joe.popularmovies.presentation.PopularMoviesViewModel
import com.joe.popularmovies.presentation.model.MovieListItemModel
import com.joe.presentation.ui.AnimatedCircularProgressBar
import com.joe.presentation.ui.ErrorScreen
import com.joe.presentation.ui.ImageShimmer
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.RefreshingState
import com.joe.presentation.viewModels.ViewModelState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesScreen(
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) { viewModel.init() }
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        PullToRefreshBox(
            isRefreshing = state is RefreshingState,
            onRefresh = viewModel::refresh
        ) {
            ScreenState(state, viewModel::getMoreMovies)
        }
    }
}


@Composable
private fun ScreenState(state: ViewModelState, getMoreMovies: (() -> Unit)?) {
    when (state) {
        is PopularMoviesSuccessState -> PopularMoviesSuccessScreen(state, getMoreMovies)
        is ErrorState -> ErrorScreen()
        is LoadingState -> PopularMoviesLoadingScreen()
        is RefreshingState -> ScreenState(state.previousState, null)
        is PopularMoviesLoadingMoreState
            -> PopularMoviesSuccessScreen(state.previousState, null)
    }
}

@Composable
fun PopularMoviesSuccessScreen(state: PopularMoviesSuccessState, getMoreMovies: (() -> Unit)?) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        val movies = state.popularMoviesModel.movies
        items(movies.size) { index ->
            MovieListItem(movies[index])
        }
//        item {
//            LaunchedEffect(true) { getMoreMovies?.invoke() }
//        }
    }
}

@Composable
fun MovieListItem(movie: MovieListItemModel) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(contentAlignment = Alignment.BottomStart) {
            PosterImage(movie.posterPath)
            AnimatedCircularProgressBar(movie.score)
        }
        Column{
            Text(movie.title)
            Text(movie.releaseDate)
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
            loading = { ImageShimmer() },
            error = { painterResource(R.drawable.poster_fallback) }
        )
    }
}