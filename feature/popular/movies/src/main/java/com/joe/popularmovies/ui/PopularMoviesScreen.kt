package com.joe.popularmovies.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.joe.popular.ui.PopularScreen
import com.joe.popularmovies.presentation.PopularMoviesViewModel
import com.joe.presentation.R
import com.joe.presentation.ui.navigation.Screens

@Composable
fun PopularMoviesScreen(
    navController: NavController? = null,
    viewModel: PopularMoviesViewModel = hiltViewModel()
){
    val movies = viewModel.itemsPager.collectAsLazyPagingItems()

    PopularScreen(
        onItemClick = { movieId -> navigateToMovieDetails(navController, movieId) },
        items = movies,
        title = stringResource(R.string.popularMovies)
    )
}

private fun navigateToMovieDetails(
    navController: NavController?,
    movieId: Int
) {
    navController?.navigate("${Screens.MovieDetails.route}/${movieId}")
}