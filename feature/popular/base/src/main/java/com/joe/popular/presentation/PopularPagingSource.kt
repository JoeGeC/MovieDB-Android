package com.joe.popular.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joe.popular.domain.PopularUseCase
import com.joe.popular.domain.entity.MediaListEntity
import com.joe.popular.presentation.converter.toModel
import com.joe.popular.presentation.model.MediaListItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PopularPagingSource(
    private val useCase: PopularUseCase
) : PagingSource<Int, MediaListItemModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaListItemModel> =
        withContext(Dispatchers.IO) {
            try {
                val page = params.key ?: 1
                val result = useCase.getItems(page)
                if (result.isSuccess) {
                    loadNextPage(result.body, page)
                } else {
                    LoadResult.Error(Throwable("Error loading data"))
                }
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

    private fun loadNextPage(result: MediaListEntity?, page: Int): LoadResult.Page<Int, MediaListItemModel> {
        val model = result?.toModel() ?: throw NullPointerException()
        return LoadResult.Page(
            data = model.items,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (model.isFinalPage == true) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, MediaListItemModel>): Int? =
        state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
}
