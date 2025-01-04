package com.joe.populartvshows.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.joe.popular.ui.PopularScreen
import com.joe.populartvshows.presentation.PopularTvShowsViewModel

@Composable
fun PopularTvShowsScreen(
    navController: NavController? = null,
    viewModel: PopularTvShowsViewModel = hiltViewModel()
){
    val shows = viewModel.itemsPager.collectAsLazyPagingItems()

    PopularScreen(
        navController = navController,
        items = shows,
    )
}