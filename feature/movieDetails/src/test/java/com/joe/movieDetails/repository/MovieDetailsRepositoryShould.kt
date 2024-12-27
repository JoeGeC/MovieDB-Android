package com.joe.movieDetails.repository

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.movieDetails.repository.boundary.MovieDetailsLocal
import com.joe.movieDetails.repository.boundary.MovieDetailsRemote
import com.joe.movieDetails.repository.repository.MovieDetailsRepositoryImpl
import com.joe.movieDetails.resources.MockEntity
import com.joe.movieDetails.resources.MockResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsRepositoryImplTest {
    private lateinit var repository: MovieDetailsRepositoryImpl
    private val remote: MovieDetailsRemote = mock()
    private val local: MovieDetailsLocal = mock()

    @Before
    fun setup() {
        repository = MovieDetailsRepositoryImpl(remote, local)
    }

    @Test
    fun `getMovieDetails retrieves from local when data is available`() = runTest {
        whenever(local.getMovieDetails(MockEntity.MOVIE_ID))
            .thenReturn(Either.Success(MockResponse.response))

        val result = repository.getMovieDetails(MockEntity.MOVIE_ID)

        verify(local, times(1)).getMovieDetails(any())
        verify(remote, never()).getMovieDetails(any())
        assertTrue(result.isSuccess)
        assertEquals(MockEntity.entity, result.body)
    }

    @Test
    fun `getMovieDetails retrieves from remote when local data is unavailable`() = runTest {
        whenever(local.getMovieDetails(MockEntity.MOVIE_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getMovieDetails(MockEntity.MOVIE_ID))
            .thenReturn(Either.Success(MockResponse.response))

        val result = repository.getMovieDetails(MockEntity.MOVIE_ID)

        verify(local, times(1)).getMovieDetails(any())
        verify(remote, times(1)).getMovieDetails(any())
        assertTrue(result.isSuccess)
        assertEquals(MockEntity.entity, result.body)
    }

    @Test
    fun `getMovieDetails returns Failure when remote fails`() = runTest {
        whenever(local.getMovieDetails(MockEntity.MOVIE_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getMovieDetails(MockEntity.MOVIE_ID))
            .thenReturn(Either.Failure(ErrorResponse("Remote error")))

        val result = repository.getMovieDetails(MockEntity.MOVIE_ID)

        verify(local, times(1)).getMovieDetails(any())
        verify(remote, times(1)).getMovieDetails(any())
        assertTrue(result.isFailure)
    }

    @Test
    fun `getMovieDetails handles null response from remote`() = runTest {
        whenever(local.getMovieDetails(MockEntity.MOVIE_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getMovieDetails(MockEntity.MOVIE_ID)).thenReturn(Either.Success(null))

        val result = repository.getMovieDetails(MockEntity.MOVIE_ID)

        verify(local, times(1)).getMovieDetails(any())
        verify(remote, times(1)).getMovieDetails(any())
        assertTrue(result.isFailure)
    }

    @Test
    fun `getMovieDetails inserts into local when remote succeeds`() = runTest {
        whenever(local.getMovieDetails(MockEntity.MOVIE_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getMovieDetails(MockEntity.MOVIE_ID))
            .thenReturn(Either.Success(MockResponse.response))

        repository.getMovieDetails(MockEntity.MOVIE_ID)

        verify(local, times(1)).getMovieDetails(any())
        verify(remote, times(1)).getMovieDetails(any())
        verify(local, times(1)).insert(MockResponse.response)
    }

}
