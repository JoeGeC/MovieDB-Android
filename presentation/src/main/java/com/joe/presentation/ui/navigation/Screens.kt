package com.joe.presentation.ui.navigation

sealed class Screens(val route: String, val param: String? = null) {
    object Movies : Screens("movies_route")
    object TvShows : Screens("tv_shows_route")
    object MovieDetails : Screens("movie_details_route", "movieId")
}