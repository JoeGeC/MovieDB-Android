package com.joe.feature.presentation

import com.joe.feature.presentation.model.MovieDetailsModel

abstract class MovieDetailsState()

class LoadingState() : MovieDetailsState()

class ErrorState() : MovieDetailsState(){
    override fun equals(other: Any?): Boolean {
        return other is ErrorState
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

data class MovieDetailsSuccessState(val movieDetails: MovieDetailsModel) : MovieDetailsState()

data class MovieDetailsRefreshingState(val previousState: MovieDetailsState) : MovieDetailsState()

data class MovieDetailsCompletedState(val previousState: MovieDetailsState) : MovieDetailsState()