package com.joe.cast.local

import com.joe.cast.resources.MockEntity
import com.joe.cast.resources.MockLocal
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CastLocalShould {
    private lateinit var local: CastLocalImpl
    private val mockDaoHelper: CastDaoHelper = mock()

    @Before
    fun setup() {
        local = CastLocalImpl(mockDaoHelper)
    }

    @Test
    fun `call dao insert with correct data`() = runTest {
        local.insert(MockLocal.model)

        verify(mockDaoHelper).insert(MockLocal.model)
    }

    @Test
    fun `handle exceptions gracefully on insert`() = runTest {
        whenever(mockDaoHelper.insert(any())).thenThrow(RuntimeException("Database error"))

        local.insert(MockLocal.model)

        verify(mockDaoHelper).insert(any())
    }

    @Test
    fun `return success when dao returns data on get`() = runTest {
        whenever(mockDaoHelper.getById(MockEntity.CAST_LIST_ID)).thenReturn(MockLocal.model)

        val result = local.getCastOf(MockEntity.CAST_LIST_ID)

        assertTrue(result is Either.Success)
        assertEquals(MockLocal.model, result.body)
    }

    @Test
    fun `return failure when dao returns null on get`() = runTest {
        whenever(mockDaoHelper.getById(MockEntity.CAST_LIST_ID)).thenReturn(null)

        val result = local.getCastOf(MockEntity.CAST_LIST_ID)

        assertTrue(result is Either.Failure)
        assertTrue(result.errorBody is ErrorResponse)
    }
}