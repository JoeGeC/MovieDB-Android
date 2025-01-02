package com.joe.popularmovies.presentation.model

data class MovieListItemModel(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val score: Float,
){
    override fun equals(other: Any?): Boolean =
        other is MovieListItemModel
                && this.id == other.id
                && this.title == other.title
                && this.releaseDate == other.releaseDate
                && this.score == other.score

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + id
        result = 31 * result + title.hashCode()
        result = 31 * result + releaseDate.hashCode()
        result = 31 * result + posterPath.hashCode()
        result = 31 * result + score.hashCode()
        return result
    }
}