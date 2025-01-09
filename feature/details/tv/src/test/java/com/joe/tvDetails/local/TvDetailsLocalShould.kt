package com.joe.tvDetails.local

import com.joe.base.local.DetailsLocalImpl
import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.tvDetails.local.dao.TvDetailsDao
import com.joe.tvDetails.resources.MockLocal
import com.joe.tvDetails.resources.MockEntity
import com.joe.tvDetails.local.dao.TvDetailsDaoHelper
import com.joe.tvDetails.local.model.TvDetailsLocalModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class TvDetailsLocalShould {
    private lateinit var local: DetailsLocalImpl<TvDetailsLocalModel>
    private val mockDatabase: TvDatabase = mock()
    private val mockDao: TvDetailsDao = mock()
    private val daoHelper = TvDetailsDaoHelper(mockDao)

    @Before
    fun setup() {
        whenever(mockDatabase.tvDetailsDao()).thenReturn(mockDao)
        local = DetailsLocalImpl(daoHelper)
    }

    @Test
    fun `insert should call dao insertAll with correct data`() = runTest {
        local.insert(MockLocal.model)

        verify(mockDao).insertAll(MockLocal.model)
    }

    @Test
    fun `insert should handle exceptions gracefully`() = runTest {
        whenever(mockDao.insertAll(any())).thenThrow(RuntimeException("Database error"))

        local.insert(MockLocal.model)

        verify(mockDao).insertAll(any())
    }

    @Test
    fun `getMovieDetails should return success when dao returns data`() = runTest {
        whenever(mockDao.getById(MockEntity.ID)).thenReturn(MockLocal.model)

        val result = local.getById(MockEntity.ID)

        assertTrue(result is Either.Success)
        assertEquals(MockLocal.model, result.body)
    }

    @Test
    fun `getMovieDetails should return failure when dao returns null`() = runTest {
        whenever(mockDao.getById(MockEntity.ID)).thenReturn(null)

        val result = local.getById(MockEntity.ID)

        assertTrue(result is Either.Failure)
        assertTrue(result.errorBody is ErrorResponse)
    }
}