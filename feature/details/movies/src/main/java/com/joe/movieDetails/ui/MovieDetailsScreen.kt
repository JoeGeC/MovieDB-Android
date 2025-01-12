package com.joe.movieDetails.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joe.base.presentation.DetailsSuccessState
import com.joe.base.presentation.MediaDetailsModel
import com.joe.base.ui.DetailsScreenState
import com.joe.base.ui.DetailsSuccessScreen
import com.joe.base.ui.MediaTitle
import com.joe.base.ui.Overview
import com.joe.base.ui.ReleaseDate
import com.joe.base.ui.Tagline
import com.joe.movieDetails.presentation.MovieDetailsViewModel
import com.joe.movieDetails.presentation.model.MovieDetailsModel
import com.joe.moviesCast.ui.MovieCastListScreen
import com.joe.presentation.ui.ErrorScreen
import com.joe.presentation.viewModels.ViewModelState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    movieId: Int?,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(movieId) {
        viewModel.init(movieId)
    }
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ScreenState(state, viewModel::refresh)
    }
}

@Composable
fun ScreenState(state: ViewModelState, refresh: (() -> Unit)? = null) {
    when (state) {
        is DetailsSuccessState<*> -> {
            if (state.mediaDetails !is MovieDetailsModel)
                ErrorScreen()
            else DetailsSuccessScreen(state.mediaDetails) { scrollState ->
                MovieDetailsSurface(state.mediaDetails, scrollState)
            }
        }
        else -> DetailsScreenState(state, refresh)
    }
}

@Composable
private fun MovieDetailsSurface(mediaDetails: MediaDetailsModel, scrollState: ScrollState) {
    mediaDetails as MovieDetailsModel
    Surface(
        modifier = Modifier
            .padding(top = 140.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(Modifier.padding(bottom = 26.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp, bottom = 16.dp)
                    .padding(horizontal = 28.dp),
            ) {
                MediaTitle(mediaDetails.name)
                ReleaseDate(mediaDetails.releaseDate)
                Tagline(mediaDetails.tagline)
                Overview(mediaDetails.overview)
            }
            MovieCastListScreen(mediaDetails.id, scrollState)
        }
    }
}