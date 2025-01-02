package com.joe.popularmovies.local

import com.joe.core.entity.Either
import com.joe.popularmovies.resources.MockLocal
import com.joe.popularmovies.resources.MockObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class PopularMoviesLocalShould {
    private lateinit var local: PopularMoviesLocalImpl
    private val mockDatabase: PopularMoviesDatabase = mock()
    private val mockDao: PopularMoviesDao = mock()

    @Before
    fun setup() {
        whenever(mockDatabase.popularMoviesDao()).thenReturn(mockDao)
        local = PopularMoviesLocalImpl(mockDatabase)
    }

    @Test
    fun `return success on get when dao returns data`() = runTest {
        whenever(
            mockDao.getByPage(
                eq(MockObjects.PAGE_1),
                any()
            )
        ).thenReturn(MockLocal.model)

        val result = local.get(MockObjects.PAGE_1)

        println(result)
        assertTrue(result is Either.Success)
        assertEquals(MockLocal.model, result.body)
    }

    @Test
    fun `return failure on get when no data exists`() = runTest {
        whenever(mockDao.getByPage(MockObjects.PAGE_1, MockLocal.validTime)).thenReturn(null)

        val result = local.get(MockObjects.PAGE_1)

        assertTrue(result is Either.Failure)
        assertEquals("Cache expired or no data available", (result as Either.Failure).error.statusMessage)
    }

    @Test
    fun `update timestamp and call insertAll on insert`() = runTest {
        local.insert(MockLocal.model)

        verify(mockDao, times(1)).insertAll(
            check {
                assertEquals(MockObjects.PAGE_1, it.page)
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