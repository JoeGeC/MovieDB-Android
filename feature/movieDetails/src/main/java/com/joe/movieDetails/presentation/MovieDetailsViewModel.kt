package com.joe.movieDetails.presentation

import androidx.lifecycle.ViewModel
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.core.entity.MediaDetailsEntity
import com.joe.movieDetails.domain.usecase.MovieDetailsUseCase
import com.joe.core.converter.toModel
import com.joe.core.viewModels.ErrorState
import com.joe.core.viewModels.LoadingState
import com.joe.core.viewModels.RefreshingState
import com.joe.core.viewModels.ViewModelState
import com.joe.core.viewModels.job
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieDetailsViewModel(
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _state = MutableStateFlow<ViewModelState>(LoadingState())
    val state: StateFlow<ViewModelState> = _state.asStateFlow()
    private var movieId: Int? = null

    fun init(movieId: Int) {
        this.movieId = movieId
        getMovie()
    }

    private fun getMovie(){
        if(movieId == null){
            _state.value = ErrorState()
            return
        }
        job({
            try {
                val result = movieDetailsUseCase.getMovieDetails(movieId!!)
                handleResult(result)
            } catch (_: Exception){
                _state.value = ErrorState()
            }
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

    fun refresh() {
        if (_state.value is LoadingState || _state.value is RefreshingState) return
        _state.value = RefreshingState(_state.value)
        getMovie()
    }

}