package com.joe.popularmovies.presentation

import androidx.lifecycle.ViewModel
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.core.job
import com.joe.core.model.MediaDetailsModel
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.domain.usecase.PopularMoviesUseCase
import com.joe.popularmovies.presentation.converter.toModel
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
    private val _state = MutableStateFlow<PopularMoviesState>(LoadingState())
    val state: StateFlow<PopularMoviesState> = _state.asStateFlow()
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
                .catch { e -> _state.value = ErrorState() }
                .onCompletion { _state.value = PopularMoviesCompletedState(_state.value) }
                .collect { result -> _state.value = handleResult(result) }
        }, dispatcher)
    }

    private fun handleResult(result: Either<PopularMoviesEntity?, ErrorEntity?>) =
        when {
            result.isSuccess -> handleSuccessState(result.body)
            _state.value.getBaseState() is PopularMoviesSuccessState -> _state.value.getBaseState()
            else -> ErrorState()
        }

    private fun handleSuccessState(entity: PopularMoviesEntity?) =
        if (entity != null) {
            canLoadMore = !entity.isFinalPage
            currentPage++
            val model = entity.toModel(getCurrentMoviesInState())
            if (model.movies.isEmpty()) ErrorState()
            else PopularMoviesSuccessState(model)
        } else ErrorState()

    private fun getCurrentMoviesInState(): List<MediaDetailsModel> {
        val currentState = _state.value.getBaseState() as? PopularMoviesLoadingMoreState
        return currentState?.popularMoviesModel?.movies.orEmpty()
    }

    fun getMoreMovies() {
        if (!canLoadMore) return
        val currentState = _state.value.getBaseState() as? PopularMoviesSuccessState ?: return
        _state.value = PopularMoviesLoadingMoreState(currentState.popularMoviesModel)
        getMovies()
    }

    fun refresh() {
        if (_state.value is LoadingState || _state.value is PopularMoviesRefreshingState) return
        reset()
        _state.value = PopularMoviesRefreshingState(_state.value)
        getMovies()
    }

    private fun reset() {
        currentPage = 1
        canLoadMore = true
    }

}