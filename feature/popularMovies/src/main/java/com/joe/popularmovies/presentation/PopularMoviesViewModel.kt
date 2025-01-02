package com.joe.popularmovies.presentation

import androidx.lifecycle.ViewModel
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.domain.usecase.PopularMoviesUseCase
import com.joe.popularmovies.presentation.converter.toModel
import com.joe.popularmovies.presentation.model.MovieListItemModel
import com.joe.presentation.IoDispatcher
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.RefreshingState
import com.joe.presentation.viewModels.ViewModelState
import com.joe.presentation.viewModels.job
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state = MutableStateFlow<ViewModelState>(LoadingState())
    val state: StateFlow<ViewModelState> = _state.asStateFlow()
    private var currentJob: Job? = null
    private var currentPage = 1
    private var canLoadMore = true

    fun init() {
        getMovies()
    }

    private fun getMovies() {
        currentJob?.cancel()
        currentJob = job({
            val result = popularMoviesUseCase.getPopularMovies(currentPage)
            _state.value = handleResult(result)
        }, dispatcher)
    }

    private fun handleResult(result: Either<PopularMoviesEntity?, ErrorEntity?>): ViewModelState =
        when {
            result.isSuccess -> handleSuccessState(result.body)
            _state.value.getBaseState() is PopularMoviesSuccessState -> _state.value.getBaseState()
            else -> ErrorState()
        }

    private fun handleSuccessState(entity: PopularMoviesEntity?): ViewModelState = when {
        entity == null -> getPreviousStateIfAvailable()
        isMoviesEmpty(entity) -> ErrorState()
        else -> createSuccessState(entity)
    }

    private fun isMoviesEmpty(entity: PopularMoviesEntity): Boolean {
        val model = entity.toModel(getCurrentMoviesInState())
        return model.movies.isEmpty()
    }

    private fun createSuccessState(entity: PopularMoviesEntity): PopularMoviesSuccessState {
        canLoadMore = !entity.isFinalPage
        currentPage++
        val model = entity.toModel(getCurrentMoviesInState())
        return PopularMoviesSuccessState(model)
    }

    private fun getPreviousStateIfAvailable(): ViewModelState =
        if (_state.value.getBaseState() is PopularMoviesSuccessState)
            _state.value.getBaseState()
        else ErrorState()

    private fun getCurrentMoviesInState(): List<MovieListItemModel> {
        val currentState = _state.value.getBaseState() as? PopularMoviesSuccessState
        return currentState?.popularMoviesModel?.movies.orEmpty()
    }

    fun getMoreMovies() {
        if (!canLoadMore || _state.value is PopularMoviesLoadingMoreState || _state.value is RefreshingState) return
        val currentState = _state.value.getBaseState() as? PopularMoviesSuccessState ?: return
        _state.value = PopularMoviesLoadingMoreState(currentState)
        getMovies()
    }

    fun refresh() {
        if (_state.value is RefreshingState) return
        reset()
        _state.value = RefreshingState(_state.value)
        getMovies()
    }

    private fun reset() {
        currentPage = 1
        canLoadMore = true
    }

}