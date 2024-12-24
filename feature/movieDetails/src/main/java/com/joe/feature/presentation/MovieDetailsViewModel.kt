package com.joe.feature.presentation

import androidx.lifecycle.ViewModel
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.feature.domain.entity.MovieDetailsEntity
import com.joe.feature.domain.usecase.MovieDetailsUseCase
import com.joe.feature.presentation.converter.toModel
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
                .collect { result ->
                    when {
                        result.isSuccess -> handleSuccessState(result)
                        else -> _state.value = ErrorState()
                    }
                }
        }, dispatcher)
    }

    private fun handleSuccessState(result: Either<MovieDetailsEntity?, ErrorEntity?>) {
        val body = result.body
        _state.value = (if (body != null) {
            MovieDetailsSuccessState(body.toModel())
        } else ErrorState())
    }

}