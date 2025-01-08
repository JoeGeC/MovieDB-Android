package com.joe.populartvshows.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.joe.popular.ui.PopularScreen
import com.joe.populartvshows.presentation.PopularTvShowsViewModel
import com.joe.presentation.R
import com.joe.presentation.ui.navigation.Screens

@Composable
fun PopularTvShowsScreen(
    navController: NavController? = null,
    viewModel: PopularTvShowsViewModel = hiltViewModel()
){
    val movies = viewModel.itemsPager.collectAsLazyPagingItems()

    PopularScreen(
        onItemClick = { tvShowId -> navigateToTvDetails(navController, tvShowId) },
        items = movies,
        title = stringResource(R.string.popularTvShows)
    )
}

private fun navigateToTvDetails(
    navController: NavController?,
    tvShowId: Int
) {
    navController?.navigate("${Screens.TvDetails.route}/${tvShowId}")
}