package com.joe.populartvshows.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.joe.popular.ui.PopularScreen
import com.joe.populartvshows.presentation.PopularTvShowsViewModel
import com.joe.presentation.R

@Composable
fun PopularTvShowsScreen(
    navController: NavController? = null,
    viewModel: PopularTvShowsViewModel = hiltViewModel()
){
    val shows = viewModel.itemsPager.collectAsLazyPagingItems()

    PopularScreen(
        navController = navController,
        items = shows,
        title = stringResource(R.string.popularTvShows)
    )
}