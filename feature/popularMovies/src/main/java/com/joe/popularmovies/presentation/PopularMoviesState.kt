package com.joe.popularmovies.presentation

import com.joe.popularmovies.presentation.model.PopularMoviesModel

data class PopularMoviesLoadingMoreState(
    val previousState: PopularMoviesSuccessState
) : com.joe.presentation.viewModels.ViewModelState() {
    override fun getBaseState() = previousState
}

data class PopularMoviesSuccessState(
    val popularMoviesModel: PopularMoviesModel,
) : com.joe.presentation.viewModels.ViewModelState()