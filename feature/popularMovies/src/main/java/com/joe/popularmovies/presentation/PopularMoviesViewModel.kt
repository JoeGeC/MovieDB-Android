package com.joe.popularmovies.presentation

import androidx.lifecycle.ViewModel
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.domain.usecase.PopularMoviesUseCase
import com.joe.popularmovies.presentation.converter.toModel
import com.joe.presentation.viewModels.job
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion

class PopularMoviesViewModel(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _state = MutableStateFlow<com.joe.presentation.viewModels.ViewModelState>(com.joe.presentation.viewModels.LoadingState())
    val state: StateFlow<com.joe.presentation.viewModels.ViewModelState> = _state.asStateFlow()
    private var currentJob: Job? = null
    private var currentPage = 1
    private var canLoadMore = true

    fun init() {
        getMovies()
    }

    private fun getMovies() {
        currentJob?.cancel()
        currentJob = job({
            popularMoviesUseCase.getPopularMovies(currentPage)
                .catch { e -> _state.value = com.joe.presentation.viewModels.ErrorState() }
                .onCompletion { _state.value =
                    com.joe.presentation.viewModels.CompletedState(_state.value)
                }
                .collect { result -> _state.value = handleResult(result) }
        }, dispatcher)
    }

    private fun handleResult(result: Either<PopularMoviesEntity?, ErrorEntity?>): com.joe.presentation.viewModels.ViewModelState =
        when {
            result.isSuccess -> handleSuccessState(result.body)
            _state.value.getBaseState() is PopularMoviesSuccessState -> _state.value.getBaseState()
            else -> com.joe.presentation.viewModels.ErrorState()
        }

    private fun handleSuccessState(entity: PopularMoviesEntity?) =
        if (entity != null) {
            canLoadMore = !entity.isFinalPage
            currentPage++
            val model = entity.toModel(getCurrentMoviesInState())
            if (model.movies.isEmpty()) com.joe.presentation.viewModels.ErrorState()
            else PopularMoviesSuccessState(model)
        } else com.joe.presentation.viewModels.ErrorState()

    private fun getCurrentMoviesInState(): List<com.joe.presentation.model.MediaDetailsModel> {
        val currentState = _state.value.getBaseState() as? PopularMoviesSuccessState
        return currentState?.popularMoviesModel?.movies.orEmpty()
    }

    fun getMoreMovies() {
        if (!canLoadMore || _state.value !is com.joe.presentation.viewModels.CompletedState) return
        val currentState = _state.value.getBaseState() as? PopularMoviesSuccessState ?: return
        _state.value = PopularMoviesLoadingMoreState(currentState)
        getMovies()
    }

    fun refresh() {
        if (_state.value is com.joe.presentation.viewModels.LoadingState || _state.value is com.joe.presentation.viewModels.RefreshingState) return
        reset()
        _state.value = com.joe.presentation.viewModels.RefreshingState(_state.value)
        getMovies()
    }

    private fun reset() {
        currentPage = 1
        canLoadMore = true
    }

}