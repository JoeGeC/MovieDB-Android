package com.joe.popularmovies.presentation

import com.joe.popularmovies.presentation.model.PopularMoviesModel

abstract class PopularMoviesState() {
    open fun getBaseState(): PopularMoviesState = this
}

class LoadingState() : PopularMoviesState()

class EmptyState() : PopularMoviesState()

class ErrorState() : PopularMoviesState() {
    override fun equals(other: Any?): Boolean {
        return other is ErrorState
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

data class PopularMoviesLoadingMoreState(
    val previousState: PopularMoviesSuccessState
) : PopularMoviesState() {
    override fun getBaseState() = previousState
}

data class PopularMoviesSuccessState(
    val popularMoviesModel: PopularMoviesModel,
) : PopularMoviesState()

data class PopularMoviesRefreshingState(
    val previousState: PopularMoviesState
) : PopularMoviesState() {
    override fun getBaseState() = previousState
}

data class PopularMoviesCompletedState(
    val previousState: PopularMoviesState
) : PopularMoviesState() {
    override fun getBaseState() = previousState
}