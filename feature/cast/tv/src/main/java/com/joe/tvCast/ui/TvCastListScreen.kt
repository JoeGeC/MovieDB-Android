package com.joe.tvCast.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.joe.cast.ui.CastListState
import com.joe.tvCast.presentation.TvCastViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvCastListScreen(
    tvShowId: Int?,
    detailsListState: ScrollState,
    viewModel: TvCastViewModel = hiltViewModel(),
) {
    LaunchedEffect(tvShowId) {
        viewModel.getCastOf(tvShowId)
    }
    val state by viewModel.state.collectAsState()

    CastListState(state, detailsListState) { viewModel.refresh(tvShowId) }
}
