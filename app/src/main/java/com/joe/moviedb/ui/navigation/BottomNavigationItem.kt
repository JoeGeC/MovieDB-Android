package com.joe.moviedb.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

internal val bottomNavigationItems: List<BottomNavigationItem> = listOf(
    BottomNavigationItem(
        label = "Movies",
        icon = Icons.Filled.Star,
        route = Screens.Movies.route
    ),
    BottomNavigationItem(
        label = "Tv Shows",
        icon = Icons.Filled.PlayArrow,
        route = Screens.TvShows.route
    ),
)

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
)