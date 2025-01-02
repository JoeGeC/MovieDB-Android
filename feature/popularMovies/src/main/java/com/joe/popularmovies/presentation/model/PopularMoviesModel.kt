package com.joe.popularmovies.presentation.model

data class PopularMoviesModel(
    val page: Int,
    val movies: List<MovieListItemModel>,
    val isFinalPage: Boolean,
){
    override fun equals(other: Any?): Boolean =
        other is PopularMoviesModel
                && this.page == other.page
                && this.movies == other.movies
                && this.isFinalPage == other.isFinalPage

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + page
        result = 31 * result + movies.hashCode()
        result = 31 * result + isFinalPage.hashCode()
        return result
    }
}