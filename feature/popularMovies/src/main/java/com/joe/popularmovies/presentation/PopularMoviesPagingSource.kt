package com.joe.popularmovies.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joe.popularmovies.domain.usecase.PopularMoviesUseCase
import com.joe.popularmovies.presentation.converter.toModel
import com.joe.popularmovies.presentation.model.MovieListItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PopularMoviesPagingSource(
    private val popularMoviesUseCase: PopularMoviesUseCase
) : PagingSource<Int, MovieListItemModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListItemModel> =
        withContext(Dispatchers.IO){
            try {
                val page = params.key ?: 1
                val result = popularMoviesUseCase.getPopularMovies(page)

                println(result)
                if (result.isSuccess) {
                    val entity = result.body
                    val movies = entity?.toModel(emptyList())?.movies ?: emptyList()

                    LoadResult.Page(
                        data = movies,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (entity?.isFinalPage == true) null else page + 1
                    )
                } else {
                    println("Error loading data")
                    LoadResult.Error(Throwable("Error loading data"))
                }
            } catch (e: Exception) {
                println("Exception: $e")
                LoadResult.Error(e)
            }
        }

    override fun getRefreshKey(state: PagingState<Int, MovieListItemModel>): Int? =
        state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
}
