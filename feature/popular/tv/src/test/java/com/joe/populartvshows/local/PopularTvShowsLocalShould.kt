package com.joe.populartvshows.local

import com.joe.core.entity.Either
import com.joe.popular.local.PopularLocalImpl
import com.joe.populartvshows.local.dao.PopularTvShowsDao
import com.joe.populartvshows.local.dao.PopularTvShowsDaoHelper
import com.joe.populartvshows.local.database.PopularTvShowsDatabase
import com.joe.populartvshows.local.model.PopularTvShowsLocalModel
import com.joe.populartvshows.resources.MockEntity
import com.joe.populartvshows.resources.MockLocal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class PopularTvShowsLocalShould {
    private lateinit var local: PopularLocalImpl<PopularTvShowsLocalModel>
    private val mockDatabase: PopularTvShowsDatabase = mock()
    private val mockDao: PopularTvShowsDao = mock()
    private val daoHelper = PopularTvShowsDaoHelper(mockDao)

    @Before
    fun setup() {
        whenever(mockDatabase.popularTvShowsDao()).thenReturn(mockDao)
        local = PopularLocalImpl(daoHelper)
    }

    @Test
    fun `return success on get when dao returns data`() = runTest {
        whenever(mockDao.getByPage(eq(MockEntity.PAGE_1), any())).thenReturn(MockLocal.model)

        val result = local.get(MockEntity.PAGE_1)

        println(result)
        assertTrue(result is Either.Success)
        assertEquals(MockLocal.model, result.body)
    }

    @Test
    fun `return failure on get when no data exists`() = runTest {
        whenever(mockDao.getByPage(MockEntity.PAGE_1, MockLocal.validTime)).thenReturn(null)

        val result = local.get(MockEntity.PAGE_1)

        assertTrue(result is Either.Failure)
        assertEquals("Cache expired or no data available", (result as Either.Failure).error.statusMessage)
    }

    @Test
    fun `update timestamp and call insertAll on insert`() = runTest {
        local.insert(MockLocal.model)

        verify(mockDao, times(1)).insertAll(
            check {
                assertEquals(MockEntity.PAGE_1, it.page)
                assertTrue(it.cachedAt > 0)
            }
        )
    }

    @Test
    fun `do nothing on insert when input is null`() = runTest {
        local.insert(null)

        verify(mockDao, never()).insertAll(any())
    }
}