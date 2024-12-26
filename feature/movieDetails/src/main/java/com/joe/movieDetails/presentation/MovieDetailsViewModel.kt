package com.joe.movieDetails.presentation

import androidx.lifecycle.ViewModel
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.core.entity.MediaDetailsEntity
import com.joe.movieDetails.domain.usecase.MovieDetailsUseCase
import com.joe.core.converter.toModel
import com.joe.core.job
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion

class MovieDetailsViewModel(
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _state = MutableStateFlow<MovieDetailsState>(LoadingState())
    val state: StateFlow<MovieDetailsState> = _state.asStateFlow()

    fun getMovie(movieId: Long) {
        job({
            movieDetailsUseCase.getMovieDetails(movieId)
                .catch { e -> _state.value = ErrorState() }
                .onCompletion { _state.value = MovieDetailsCompletedState(_state.value) }
                .collect { result -> handleResult(result) }
        }, dispatcher)
    }

    private fun handleResult(result: Either<MediaDetailsEntity?, ErrorEntity?>) = when {
        result.isSuccess -> handleSuccessState(result)
        else -> _state.value = ErrorState()
    }

    private fun handleSuccessState(result: Either<MediaDetailsEntity?, ErrorEntity?>) {
        val body = result.body
        _state.value = (if (body != null) {
            MovieDetailsSuccessState(body.toModel())
        } else ErrorState())
    }

    fun refresh(movieId: Long) {
        if (_state.value is LoadingState) return
        _state.value = MovieDetailsRefreshingState(_state.value)
        getMovie(movieId)
    }

}