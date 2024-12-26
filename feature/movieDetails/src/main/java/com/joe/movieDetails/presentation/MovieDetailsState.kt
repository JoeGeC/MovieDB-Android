package com.joe.movieDetails.presentation

import com.joe.core.model.MediaDetailsModel

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

data class MovieDetailsSuccessState(val movieDetails: MediaDetailsModel) : MovieDetailsState()

data class MovieDetailsRefreshingState(val previousState: MovieDetailsState) : MovieDetailsState()

data class MovieDetailsCompletedState(val previousState: MovieDetailsState) : MovieDetailsState()