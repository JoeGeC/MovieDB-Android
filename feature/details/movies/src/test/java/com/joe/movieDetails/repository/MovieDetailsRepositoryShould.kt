package com.joe.movieDetails.repository

import com.joe.base.data.DetailsRemote
import com.joe.base.local.DetailsLocal
import com.joe.base.repository.DetailsRepositoryImpl
import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.movieDetails.data.response.MovieDetailsResponse
import com.joe.movieDetails.domain.MovieDetailsEntity
import com.joe.movieDetails.local.model.MovieDetailsLocalModel
import com.joe.movieDetails.repository.converter.MovieDetailsRepositoryConverter
import com.joe.movieDetails.resources.MockEntity
import com.joe.movieDetails.resources.MockLocal
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
    private lateinit var repository: DetailsRepositoryImpl<MovieDetailsEntity, MovieDetailsLocalModel, MovieDetailsResponse>
    private val remote: DetailsRemote<MovieDetailsResponse> = mock()
    private val local: DetailsLocal<MovieDetailsLocalModel> = mock()
    private val converter = MovieDetailsRepositoryConverter()

    @Before
    fun setup() {
        repository = DetailsRepositoryImpl(remote, local, converter)
    }

    @Test
    fun `getMovieDetails retrieves from local when data is available`() = runTest {
        whenever(local.getById(MockEntity.MOVIE_ID)).thenReturn(Either.Success(MockLocal.model))

        val result = repository.getDetails(MockEntity.MOVIE_ID)

        verify(local, times(1)).getById(any())
        verify(remote, never()).getDetails(any())
        assertTrue(result.isSuccess)
        assertEquals(MockEntity.entity, result.body)
    }

    @Test
    fun `getMovieDetails retrieves from remote when local data is unavailable`() = runTest {
        whenever(local.getById(MockEntity.MOVIE_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(Either.Success(MockResponse.response))

        val result = repository.getDetails(MockEntity.MOVIE_ID)

        verify(local, times(1)).getById(any())
        verify(remote, times(1)).getDetails(any())
        assertTrue(result.isSuccess)
        assertEquals(MockEntity.entity, result.body)
    }

    @Test
    fun `getMovieDetails returns Failure when remote fails`() = runTest {
        whenever(local.getById(MockEntity.MOVIE_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(Either.Failure(ErrorResponse("Remote error")))

        val result = repository.getDetails(MockEntity.MOVIE_ID)

        verify(local, times(1)).getById(any())
        verify(remote, times(1)).getDetails(any())
        assertTrue(result.isFailure)
    }

    @Test
    fun `getMovieDetails handles null response from remote`() = runTest {
        whenever(local.getById(MockEntity.MOVIE_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getDetails(MockEntity.MOVIE_ID)).thenReturn(Either.Success(null))

        val result = repository.getDetails(MockEntity.MOVIE_ID)

        verify(local, times(1)).getById(any())
        verify(remote, times(1)).getDetails(any())
        assertTrue(result.isFailure)
    }

    @Test
    fun `getMovieDetails inserts into local when remote succeeds`() = runTest {
        whenever(local.getById(MockEntity.MOVIE_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(Either.Success(MockResponse.response))

        repository.getDetails(MockEntity.MOVIE_ID)

        verify(local, times(1)).getById(any())
        verify(remote, times(1)).getDetails(any())
        verify(local, times(1)).insert(MockLocal.model)
    }

}
