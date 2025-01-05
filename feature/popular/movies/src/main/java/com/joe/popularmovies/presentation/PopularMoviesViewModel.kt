package com.joe.popularmovies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.joe.popular.domain.PopularUseCase
import com.joe.popular.presentation.PopularPagingSource
import com.joe.popularmovies.PopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    @PopularMovies private val useCase: PopularUseCase
) : ViewModel() {

    val itemsPager = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { PopularPagingSource(useCase) }
    ).flow.cachedIn(viewModelScope)
}
