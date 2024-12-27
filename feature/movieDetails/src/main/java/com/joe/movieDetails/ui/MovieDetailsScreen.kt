package com.joe.movieDetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.joe.movieDetails.presentation.MovieDetailsSuccessState
import com.joe.movieDetails.presentation.MovieDetailsViewModel
import com.joe.presentation.model.MediaDetailsModel
import com.joe.presentation.ui.ErrorScreen
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.RefreshingState
import com.joe.presentation.viewModels.ViewModelState

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(movieId) { viewModel.init(movieId) }
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ScreenState(state)
    }
}

@Composable
private fun ScreenState(state: ViewModelState) {
    when (state) {
        is MovieDetailsSuccessState -> { MovieDetailsSuccessScreen(state.movieDetails) }
        is ErrorState -> { ErrorScreen() }
        is LoadingState -> { Text("Loading...") }
        is RefreshingState -> { ScreenState(state.previousState) }
    }
}

@Composable
fun MovieDetailsSuccessScreen(movieDetails: MediaDetailsModel) {
    Column {
        Text(text = movieDetails.title)
        Text(text = movieDetails.tagline)
        Text(text = movieDetails.overview)
        Text(text = movieDetails.releaseDate)
        Text(text = movieDetails.score.toString())
        Text(text = movieDetails.type.toString())
        Text(text = movieDetails.posterPath ?: "")
        Text(text = movieDetails.backdropPath ?: "")
    }
}