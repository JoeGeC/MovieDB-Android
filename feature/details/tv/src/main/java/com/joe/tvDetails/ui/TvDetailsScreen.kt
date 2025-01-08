package com.joe.tvDetails.ui

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.joe.presentation.ui.ErrorScreen
import com.joe.presentation.viewModels.ViewModelState
import com.joe.tvDetails.presentation.TvDetailsViewModel
import com.joe.tvDetails.presentation.model.TvDetailsModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvDetailsScreen(
    movieId: Int?,
    viewModel: TvDetailsViewModel = hiltViewModel()
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
            if (state.mediaDetails !is TvDetailsModel)
                ErrorScreen()
            else DetailsSuccessScreen(state.mediaDetails) {
                TvDetailsSurface(state.mediaDetails)
            }
        }
        else -> DetailsScreenState(state, refresh)
    }
}

@Composable
private fun TvDetailsSurface(tvDetails: MediaDetailsModel) {
    tvDetails as TvDetailsModel
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
            MediaTitle(tvDetails.name)
            ReleaseDate("${tvDetails.firstAirDate} -> ${tvDetails.lastAirDate}")
            Row{
                Seasons("${tvDetails.firstAirDate} -> ${tvDetails.lastAirDate}")
                Spacer(Modifier.weight(1f))
                Episodes("${tvDetails.firstAirDate} -> ${tvDetails.lastAirDate}")
            }
            Tagline(tvDetails.tagline)
            Overview(tvDetails.overview)
        }
    }
}

@Composable
fun Seasons(numberOfSeasons: String) {
    Text(
        "Seasons: $numberOfSeasons",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun Episodes(numberOfEpisodes: String) {
    Text(
        "Episodes: $numberOfEpisodes",
        style = MaterialTheme.typography.bodyMedium
    )
}
