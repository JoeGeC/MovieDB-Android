package com.joe.moviesCast.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.joe.cast.ui.CastListState
import com.joe.moviesCast.presentation.MovieCastViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCastListScreen(
    movieId: Int?,
    detailsListState: ScrollState,
    viewModel: MovieCastViewModel = hiltViewModel(),
) {
    LaunchedEffect(movieId) {
        viewModel.getCastOf(movieId)
    }
    val state by viewModel.state.collectAsState()

    CastListState(state, detailsListState) { viewModel.refresh(movieId) }
}
