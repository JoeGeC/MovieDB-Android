package com.joe.popularmovies.repository

import app.cash.turbine.test
import com.joe.core.entity.Either
import com.joe.popularmovies.repository.boundary.PopularMoviesLocal
import com.joe.popularmovies.repository.boundary.PopularMoviesRemote
import com.joe.popularmovies.resources.MockObjects
import com.joe.popularmovies.resources.MockResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PopularMoviesRepositoryImplTest {
    private lateinit var repository: PopularMoviesRepositoryImpl
    private val remote: PopularMoviesRemote = mock()
    private val local: PopularMoviesLocal = mock()

    @Before
    fun setup() {
        repository = PopularMoviesRepositoryImpl(remote, local)
    }

    @Test
    fun `emit local data when it exists`() = runTest {
        whenever(local.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.success)

        repository.getPopularMovies(MockObjects.PAGE_1).test {
            val emitted = awaitItem()
            assert(emitted is Either.Success && emitted.value?.movies?.isNotEmpty() == true)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emit remote data when local is empty and remote succeeds`() = runTest {
        whenever(local.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.emptySuccess)
        whenever(remote.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.success)

        repository.getPopularMovies(MockObjects.PAGE_1).test {
            val emitted = awaitItem()
            assert(emitted is Either.Success && emitted.value?.movies?.isNotEmpty() == true)
            cancelAndIgnoreRemainingEvents()
        }

        verify(local).insertPopularMovies(MockResponse.popularMovies)
    }

    @Test
    fun `emit error when remote and local both fail`() = runTest {
        whenever(local.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.failure)
        whenever(remote.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.failure)

        repository.getPopularMovies(MockObjects.PAGE_1).test {
            val emitted = awaitItem()
            assert(emitted is Either.Failure && emitted.error?.message == MockResponse.ERROR_MESSAGE)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `not include item when null data in movie in local response`() = runTest {
        whenever(local.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.nullMovieDataSuccess)
        whenever(remote.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.success)

        repository.getPopularMovies(MockObjects.PAGE_1).test {
            val emitted = awaitItem()
            assert(emitted is Either.Success && emitted.value == MockObjects.popularMoviesEntity1)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `not emit when null data in local response`() = runTest {
        whenever(local.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.nullDataSuccess)
        whenever(remote.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.success)

        repository.getPopularMovies(MockObjects.PAGE_1).test {
            val emitted = awaitItem()
            assert(emitted is Either.Success && emitted.value == MockObjects.popularMoviesEntity1)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emit failure when null data in remote response`() = runTest {
        whenever(local.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.failure)
        whenever(remote.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.nullDataSuccess)

        repository.getPopularMovies(MockObjects.PAGE_1).test {
            val emitted = awaitItem()
            assert(emitted is Either.Failure)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `not include item when null data in movie in data response`() = runTest {
        whenever(local.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.failure)
        whenever(remote.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.nullMovieDataSuccess)

        repository.getPopularMovies(MockObjects.PAGE_1).test {
            val emitted = awaitItem()
            assert(emitted is Either.Success && emitted.value == MockObjects.popularMoviesEntity1)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
