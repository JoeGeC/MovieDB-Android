package com.joe.movieDetails.presentation

import androidx.lifecycle.ViewModel
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.core.entity.MediaDetailsEntity
import com.joe.movieDetails.domain.usecase.MovieDetailsUseCase
import com.joe.presentation.IoDispatcher
import com.joe.presentation.converter.toModel
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.RefreshingState
import com.joe.presentation.viewModels.ViewModelState
import com.joe.presentation.viewModels.job
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
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
            _state.value = LoadingState()
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
        job({
            delay(20) //Allow compose PullToRefresh to finish animating
            getMovie()
        }, dispatcher)
    }

}