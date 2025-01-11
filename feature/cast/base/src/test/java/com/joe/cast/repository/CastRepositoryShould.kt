package com.joe.cast.repository

import com.joe.cast.data.CastRemote
import com.joe.cast.local.CastLocal
import com.joe.cast.repository.converter.CastRepositoryConverter
import com.joe.cast.resources.MockEntity
import com.joe.cast.resources.MockLocal
import com.joe.cast.resources.MockResponse
import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CastRepositoryShould {
    private lateinit var repository: CastRepositoryImpl
    private val remote: CastRemote = mock()
    private val local: CastLocal = mock()
    private val converter = CastRepositoryConverter()

    @Before
    fun setup() {
        repository = CastRepositoryImpl(remote, local, converter)
    }

    @Test
    fun `retrieve from local when data is available`() = runTest {
        whenever(local.getCastOf(MockEntity.CAST_LIST_ID)).thenReturn(Either.Success(MockLocal.castList))

        val result = repository.getCastOf(MockEntity.CAST_LIST_ID)

        verify(local, times(1)).getCastOf(any())
        verify(remote, never()).getCastOf(any())
        assertTrue(result.isSuccess)
        assertEquals(MockEntity.entity, result.body)
    }

    @Test
    fun `retrieve from remote when local data is unavailable`() = runTest {
        whenever(local.getCastOf(MockEntity.CAST_LIST_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getCastOf(MockEntity.CAST_LIST_ID))
            .thenReturn(Either.Success(MockResponse.response))

        val result = repository.getCastOf(MockEntity.CAST_LIST_ID)

        verify(local, times(1)).getCastOf(any())
        verify(remote, times(1)).getCastOf(any())
        assertTrue(result.isSuccess)
        assertEquals(MockEntity.entity, result.body)
    }

    @Test
    fun `return Failure when remote fails`() = runTest {
        whenever(local.getCastOf(MockEntity.CAST_LIST_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getCastOf(MockEntity.CAST_LIST_ID))
            .thenReturn(Either.Failure(ErrorResponse("Remote error")))

        val result = repository.getCastOf(MockEntity.CAST_LIST_ID)

        verify(local, times(1)).getCastOf(any())
        verify(remote, times(1)).getCastOf(any())
        assertTrue(result.isFailure)
    }

    @Test
    fun `handle null response from remote`() = runTest {
        whenever(local.getCastOf(MockEntity.CAST_LIST_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getCastOf(MockEntity.CAST_LIST_ID)).thenReturn(Either.Success(null))

        val result = repository.getCastOf(MockEntity.CAST_LIST_ID)

        verify(local, times(1)).getCastOf(any())
        verify(remote, times(1)).getCastOf(any())
        assertTrue(result.isFailure)
    }

    @Test
    fun `insert into local when remote succeeds`() = runTest {
        whenever(local.getCastOf(MockEntity.CAST_LIST_ID)).thenReturn(Either.Failure(null))
        whenever(remote.getCastOf(MockEntity.CAST_LIST_ID))
            .thenReturn(Either.Success(MockResponse.response))

        repository.getCastOf(MockEntity.CAST_LIST_ID)

        verify(local, times(1)).getCastOf(any())
        verify(remote, times(1)).getCastOf(any())
        verify(local, times(1)).insert(MockLocal.castList)
    }

}