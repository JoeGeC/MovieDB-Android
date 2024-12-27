package com.joe.moviedb.ui.navigation

sealed class Screens(val route: String) {
    object Movies : Screens("movies_route")
    object TvShows : Screens("tv_shows_route")
}