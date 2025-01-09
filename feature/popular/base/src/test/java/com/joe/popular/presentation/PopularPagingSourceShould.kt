package com.joe.popular.presentation

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joe.popular.domain.PopularUseCase
import com.joe.popular.resources.MockEntity
import com.joe.popular.resources.MockModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class PopularPagingSourceShould {
    private val useCase: PopularUseCase = mock()
    private lateinit var pagingSource: PopularPagingSource

    @Before
    fun setup() {
        pagingSource = PopularPagingSource(useCase)
    }

    @Test
    fun `load returns page on successful use case result`() = runTest {
        whenever(useCase.getItems(MockEntity.PAGE_1)).thenReturn(MockEntity.success1And2)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Page)
        val pageResult = result as PagingSource.LoadResult.Page
        assertEquals(MockModel.mediaListItems1And2, pageResult.data)
        assertEquals(null, pageResult.prevKey)
        assertEquals(MockEntity.PAGE_1 + 1, pageResult.nextKey)
    }

    @Test
    fun `load returns error on failed use case result`() = runTest {
        whenever(useCase.getItems(MockEntity.PAGE_1)).thenReturn(MockEntity.failure)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
        val errorResult = result as PagingSource.LoadResult.Error
        assertEquals(MockEntity.USE_CASE_ERROR_MESSAGE, errorResult.throwable.message)
    }

    @Test
    fun `load returns error on exception`() = runTest {
        whenever(useCase.getItems(any())).thenThrow(RuntimeException(MockEntity.ERROR_MESSAGE))

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
        val errorResult = result as PagingSource.LoadResult.Error
        assertEquals(MockEntity.ERROR_MESSAGE, errorResult.throwable.message)
    }

    @Test
    fun `getRefreshKey returns correct key`() {
        val state = PagingState(
            pages = listOf(
                PagingSource.LoadResult.Page(
                    data = MockModel.mediaListItems1And2,
                    prevKey = null,
                    nextKey = 3
                )
            ),
            anchorPosition = 2,
            config = PagingConfig(pageSize = 2),
            leadingPlaceholderCount = 0
        )

        val refreshKey = pagingSource.getRefreshKey(state)

        assertEquals(2, refreshKey)
    }

}