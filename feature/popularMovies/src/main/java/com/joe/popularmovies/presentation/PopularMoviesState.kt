package com.joe.popularmovies.presentation

import com.joe.core.viewModels.ViewModelState
import com.joe.popularmovies.presentation.model.PopularMoviesModel


data class PopularMoviesLoadingMoreState(
    val previousState: PopularMoviesSuccessState
) : ViewModelState() {
    override fun getBaseState() = previousState
}

data class PopularMoviesSuccessState(
    val popularMoviesModel: PopularMoviesModel,
) : ViewModelState()