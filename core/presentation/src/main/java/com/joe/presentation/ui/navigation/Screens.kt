package com.joe.presentation.ui.navigation

sealed class Screens(val route: String, val param: String? = null) {
    object Movies : Screens("popular_movies_route")
    object TvShows : Screens("popular_tv_shows_route")
    object MovieDetails : Screens("movie_details_route", "movieId")
    object TvDetails : Screens("tv_details_route", "tvId")
}