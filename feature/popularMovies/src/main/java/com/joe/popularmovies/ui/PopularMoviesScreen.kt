package com.joe.popularmovies.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.joe.popularmovies.presentation.PopularMoviesViewModel
import com.joe.popularmovies.presentation.model.MovieListItemModel
import com.joe.popularmovies.ui.sound.scrollToTopSounds
import com.joe.popularmovies.ui.sound.sfxSoundPool
import com.joe.presentation.ui.ErrorScreen
import com.joe.presentation.ui.ScrollPageWithHeader
import com.joe.presentation.R as presentationR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesScreen(
    navController: NavController? = null,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    val movies = viewModel.moviesPager.collectAsLazyPagingItems()
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    ScrollPageWithHeader(
        title = stringResource(presentationR.string.popularMovies),
        scrollBehavior = topAppBarScrollBehavior
    ) {
        when (movies.loadState.refresh) {
            is LoadState.Error -> ErrorScreen(movies::refresh)
            is LoadState.Loading -> PopularMoviesLoadingScreen()
            is LoadState.NotLoading -> PopularMoviesList(
                movies,
                topAppBarScrollBehavior,
                navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesList(
    movies: LazyPagingItems<MovieListItemModel>,
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
    navController: NavController?
) {
    val gridState = rememberLazyStaggeredGridState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(
            state = gridState,
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp,
        ) {
            items(
                count = movies.itemCount,
            ) { index ->
                movies[index]?.let { movie ->
                    MovieListItem(
                        movie,
                        onClick = { navController?.navigate("movieDetails/${movie.id}") })
                }
            }
            if (movies.loadState.append is LoadState.Loading)
                items(2) { MovieListLoadingItem() }
        }

        ScrollToTopButton(
            gridState,
            topAppBarScrollBehavior,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScrollToTopButton(
    gridState: LazyStaggeredGridState,
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier
) {
    val soundPool = sfxSoundPool()
    val (scrollSound, popSound) = scrollToTopSounds(LocalContext.current, soundPool)
    var scrollToTop by remember { mutableStateOf(false) }
    val hasScrolledDown by remember {
        derivedStateOf { gridState.firstVisibleItemIndex > 0 }
    }

    LaunchedEffect(scrollToTop) {
        if (scrollToTop) {
            soundPool.play(scrollSound, 1f, 1f, 0, 0, 1f)
            gridState.animateScrollToItem(0)
            topAppBarScrollBehavior.state.heightOffset = 0f
            soundPool.play(popSound, 1f, 1f, 0, 0, 1f)
            scrollToTop = false
        }
    }

    ListButton(
        visible = hasScrolledDown,
        onClick = { scrollToTop = true },
        modifier = modifier
    )
}

@Composable
private fun ListButton(
    visible: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut(),
        modifier = modifier.padding(16.dp)
    ) {
        FloatingActionButton(
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = { onClick() }
        ) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowUp,
                contentDescription = stringResource(presentationR.string.scroll_to_top),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
