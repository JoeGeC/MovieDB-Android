package com.joe.moviedb.ui.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.joe.moviedb.R

internal fun bottomNavigationItems(context: Context): List<BottomNavigationItem> = listOf(
    BottomNavigationItem(
        label = context.getString(R.string.movies),
        icon = Icons.Filled.Star,
        route = Screens.Movies.route
    ),
    BottomNavigationItem(
        label = context.getString(R.string.tv_shows),
        icon = Icons.Filled.PlayArrow,
        route = Screens.TvShows.route
    ),
)

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
)