package com.joe.moviedb.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joe.movieDetails.ui.MovieDetailsScreen
import com.joe.popularmovies.ui.PopularMoviesScreen
import com.joe.presentation.ui.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar() {
    var navigationSelectedItem by remember { mutableIntStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                bottomNavigationItems(LocalContext.current).forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = index == navigationSelectedItem,
                        colors = navigationBarColors(),
                        label = { Text(navigationItem.label) },
                        icon = { BottomNavigationIcon(navigationItem) },
                        onClick = {
                            navigationSelectedItem = index
                            navigateTo(navigationItem, navController)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(navController, paddingValues)
    }
}

private fun navigateTo(
    navigationItem: BottomNavigationItem,
    navController: NavHostController,
) {
    navController.navigate(navigationItem.route) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
private fun navigationBarColors(): NavigationBarItemColors = NavigationBarItemColors(
    selectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
    selectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
    selectedIndicatorColor = MaterialTheme.colorScheme.surface,
    disabledIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
)

@Composable
private fun BottomNavigationIcon(navigationItem: BottomNavigationItem) {
    Icon(
        navigationItem.icon,
        contentDescription = navigationItem.label
    )
}

@Composable
private fun NavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Movies.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(Screens.Movies.route) {
            PopularMoviesScreen(navController)
        }
        composable(Screens.TvShows.route) {
        }
        composable("${Screens.MovieDetails.route}/{${Screens.MovieDetails.param}}") { params ->
            val movieId = params.arguments?.getString(Screens.MovieDetails.param)?.toInt()
            MovieDetailsScreen(movieId)
        }
    }
}
