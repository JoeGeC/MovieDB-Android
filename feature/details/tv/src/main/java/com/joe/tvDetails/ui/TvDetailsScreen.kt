package com.joe.tvDetails.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
import com.joe.tvCast.ui.TvCastListScreen
import com.joe.tvDetails.R
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
            else DetailsSuccessScreen(state.mediaDetails) { scrollState ->
                TvDetailsSurface(state.mediaDetails, scrollState)
            }
        }

        else -> DetailsScreenState(state, refresh)
    }
}

@Composable
private fun TvDetailsSurface(tvDetails: MediaDetailsModel, scrollState: ScrollState) {
    tvDetails as TvDetailsModel
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
                    .padding(top = 120.dp)
                    .padding(horizontal = 26.dp),
            ) {
                MediaTitle(tvDetails.name)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    ReleaseDate("${tvDetails.firstAirDate} -> ${tvDetails.lastAirDate}")
                    Spacer(Modifier.weight(1f))
                    InProduction(tvDetails.inProduction)
                    Spacer(Modifier.weight(1f))
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                ) {
                    NumberOf(stringResource(R.string.seasons), tvDetails.numberOfSeasons, Modifier.weight(1f))
                    NumberOf(stringResource(R.string.episodes), tvDetails.numberOfEpisodes, Modifier.weight(1f))
                }
                Tagline(tvDetails.tagline)
                Overview(tvDetails.overview)
            }

            TvCastListScreen(tvDetails.id, scrollState)
        }
    }
}

@Composable
fun NumberOf(label: String, numberOf: Int?, modifier: Modifier = Modifier) {
    if (numberOf == null) return
    Row(modifier) {
        Text(
            "$label: ",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            numberOf.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun InProduction(inProduction: Boolean?, modifier: Modifier = Modifier) {
    if (inProduction == null || inProduction == false) return
    val boxShape = RoundedCornerShape(8.dp)
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .border(BorderStroke(2.dp, Color.Red), shape = boxShape)
            .padding(8.dp)
            .clip(boxShape)
    ) {
        Text(
            "In Production",
            style = MaterialTheme.typography.titleSmall,
            color = Color.Red
        )
    }
}
