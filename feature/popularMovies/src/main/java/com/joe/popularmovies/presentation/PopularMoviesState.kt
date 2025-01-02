package com.joe.popularmovies.presentation

import com.joe.popularmovies.presentation.model.PopularMoviesModel
import com.joe.presentation.viewModels.ViewModelState

data class PopularMoviesLoadingMoreState(
    val previousState: PopularMoviesSuccessState
) : ViewModelState() {
    override fun getBaseState() = previousState
}

data class PopularMoviesSuccessState(
    val popularMoviesModel: PopularMoviesModel,
) : ViewModelState()