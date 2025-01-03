package com.joe.popularmovies.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.domain.usecase.PopularMoviesUseCase
import com.joe.popularmovies.presentation.converter.toModel
import com.joe.popularmovies.presentation.model.MovieListItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PopularMoviesPagingSource(
    private val popularMoviesUseCase: PopularMoviesUseCase
) : PagingSource<Int, MovieListItemModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItemModel> =
        withContext(Dispatchers.IO){
            try {
                val page = params.key ?: 1
                val result = popularMoviesUseCase.getPopularMovies(page)

                if (result.isSuccess) {
                    loadNextPage(result.body, page)
                } else {
                    LoadResult.Error(Throwable("Error loading data"))
                }
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

    private fun loadNextPage(result: PopularMoviesEntity?, page: Int): LoadResult.Page<Int, MovieListItemModel> {
        val model = result?.toModel() ?: throw NullPointerException()
        return LoadResult.Page(
            data = model.movies,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (model.isFinalPage == true) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, MovieListItemModel>): Int? =
        state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
}
