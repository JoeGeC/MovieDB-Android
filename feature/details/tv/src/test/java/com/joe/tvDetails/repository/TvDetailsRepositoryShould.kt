package com.joe.tvDetails.repository

import com.joe.base.data.DetailsRemote
import com.joe.base.local.DetailsLocal
import com.joe.base.repository.DetailsRepositoryImpl
import com.joe.core.entity.Either
import com.joe.tvDetails.resources.MockEntity
import com.joe.tvDetails.resources.MockLocal
import com.joe.tvDetails.resources.MockResponseModel
import com.joe.data.response.ErrorResponse
import com.joe.tvDetails.data.response.TvDetailsResponse
import com.joe.tvDetails.domain.TvDetailsEntity
import com.joe.tvDetails.local.model.TvDetailsLocalModel
import com.joe.tvDetails.repository.converter.TvDetailsRepositoryConverter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsRepositoryImplTest {
    private lateinit var repository: DetailsRepositoryImpl<TvDetailsEntity, TvDetailsLocalModel, TvDetailsResponse>
    private val remote: DetailsRemote<TvDetailsResponse> = mock()
    private val local: DetailsLocal<TvDetailsLocalModel> = mock()
    private val converter = TvDetailsRepositoryConverter()

    @Before
    fun setup() {
        repository = DetailsRepositoryImpl(remote, local, converter)
    }

    @Test
    fun `getMovieDetails retrieves from local when data is available`() = runTest {
        whenever(local.getById(MockEntity.ID)).thenReturn(Either.Success(MockLocal.model))

        val result = repository.getDetails(MockEntity.ID)

        verify(local, times(1)).getById(any())
        verify(remote, never()).getDetails(any())
        assertTrue(result.isSuccess)
        assertEquals(MockEntity.entity, result.body)
    }

    @Test
    fun `getMovieDetails retrieves from remote when local data is unavailable`() = runTest {
        whenever(local.getById(MockEntity.ID)).thenReturn(Either.Failure(null))
        whenever(remote.getDetails(MockEntity.ID))
            .thenReturn(Either.Success(MockResponseModel.response))

        val result = repository.getDetails(MockEntity.ID)

        verify(local, times(1)).getById(any())
        verify(remote, times(1)).getDetails(any())
        assertTrue(result.isSuccess)
        assertEquals(MockEntity.entity, result.body)
    }

    @Test
    fun `getMovieDetails returns Failure when remote fails`() = runTest {
        whenever(local.getById(MockEntity.ID)).thenReturn(Either.Failure(null))
        whenever(remote.getDetails(MockEntity.ID))
            .thenReturn(Either.Failure(ErrorResponse("Remote error")))

        val result = repository.getDetails(MockEntity.ID)

        verify(local, times(1)).getById(any())
        verify(remote, times(1)).getDetails(any())
        assertTrue(result.isFailure)
    }

    @Test
    fun `getMovieDetails handles null response from remote`() = runTest {
        whenever(local.getById(MockEntity.ID)).thenReturn(Either.Failure(null))
        whenever(remote.getDetails(MockEntity.ID)).thenReturn(Either.Success(null))

        val result = repository.getDetails(MockEntity.ID)

        verify(local, times(1)).getById(any())
        verify(remote, times(1)).getDetails(any())
        assertTrue(result.isFailure)
    }

    @Test
    fun `getMovieDetails inserts into local when remote succeeds`() = runTest {
        whenever(local.getById(MockEntity.ID)).thenReturn(Either.Failure(null))
        whenever(remote.getDetails(MockEntity.ID))
            .thenReturn(Either.Success(MockResponseModel.response))

        repository.getDetails(MockEntity.ID)

        verify(local, times(1)).getById(any())
        verify(remote, times(1)).getDetails(any())
        verify(local, times(1)).insert(MockLocal.model)
    }

}
