package com.joe.popularmovies.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.SubcomposeAsyncImage
import com.joe.popularmovies.presentation.PopularMoviesLoadingMoreState
import com.joe.popularmovies.presentation.PopularMoviesSuccessState
import com.joe.popularmovies.presentation.PopularMoviesViewModel
import com.joe.popularmovies.presentation.model.MovieListItemModel
import com.joe.presentation.R
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
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.popularMovies),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            modifier = Modifier.padding(paddingValues),
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
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp,
    ) {
        val movies = state.popularMoviesModel.movies
        items(movies.size) { index ->
            MovieListItem(movies[index])
        }
    }
}

@Composable
fun MovieListItem(movie: MovieListItemModel) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
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