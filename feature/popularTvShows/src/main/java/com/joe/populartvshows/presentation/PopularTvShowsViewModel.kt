package com.joe.populartvshows.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.joe.popular.domain.PaginatedUseCase
import com.joe.popular.presentation.PaginatedPagingSource
import com.joe.populartvshows.PopularTvShows
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularTvShowsViewModel @Inject constructor(
    @PopularTvShows private val useCase: PaginatedUseCase
) : ViewModel() {

    val itemsPager = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { PaginatedPagingSource(useCase) }
    ).flow.cachedIn(viewModelScope)
}
