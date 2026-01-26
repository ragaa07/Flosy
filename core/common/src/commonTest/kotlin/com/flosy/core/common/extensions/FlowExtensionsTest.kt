package com.flosy.core.common.extensions

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FlowExtensionsTest {

    // ==================== asResult Tests ====================

    @Test
    fun test_asResult_successfulEmission_wrapsInResultSuccess() = runBlocking {
        val flow = flowOf(1, 2, 3)

        val results = flow.asResult().toList()

        assertEquals(3, results.size)
        assertTrue(results[0].isSuccess)
        assertTrue(results[1].isSuccess)
        assertTrue(results[2].isSuccess)
        assertEquals(1, results[0].getOrNull())
        assertEquals(2, results[1].getOrNull())
        assertEquals(3, results[2].getOrNull())
    }

    @Test
    fun test_asResult_singleValue_wrapsInResultSuccess() = runBlocking {
        val flow = flowOf("test")

        val results = flow.asResult().toList()

        assertEquals(1, results.size)
        assertTrue(results[0].isSuccess)
        assertEquals("test", results[0].getOrNull())
    }

    @Test
    fun test_asResult_emptyFlow_returnsEmptyList() = runBlocking {
        val flow = flowOf<Int>()

        val results = flow.asResult().toList()

        assertTrue(results.isEmpty())
    }

    @Test
    fun test_asResult_flowThrowsException_wrapsInResultFailure() = runBlocking {
        val exception = RuntimeException("Test error")
        val flow = flow<Int> {
            emit(1)
            throw exception
        }

        val results = flow.asResult().toList()

        assertEquals(2, results.size)
        assertTrue(results[0].isSuccess)
        assertEquals(1, results[0].getOrNull())
        assertTrue(results[1].isFailure)
        assertEquals("Test error", results[1].exceptionOrNull()?.message)
    }

    @Test
    fun test_asResult_flowThrowsImmediately_wrapsInResultFailure() = runBlocking {
        val exception = IllegalStateException("Immediate error")
        val flow = flow<String> {
            throw exception
        }

        val results = flow.asResult().toList()

        assertEquals(1, results.size)
        assertTrue(results[0].isFailure)
        assertEquals("Immediate error", results[0].exceptionOrNull()?.message)
    }

    // ==================== mapResult Tests ====================

    @Test
    fun test_mapResult_successfulResults_transformsValues() = runBlocking {
        val flow = flowOf(1, 2, 3).asResult()

        val results = flow.mapResult { it * 2 }.toList()

        assertEquals(3, results.size)
        assertEquals(2, results[0].getOrNull())
        assertEquals(4, results[1].getOrNull())
        assertEquals(6, results[2].getOrNull())
    }

    @Test
    fun test_mapResult_failedResult_passesThrough() = runBlocking {
        val exception = RuntimeException("Error")
        val flow = flow {
            emit(Result.success(1))
            emit(Result.failure<Int>(exception))
            emit(Result.success(3))
        }

        val results = flow.mapResult { it * 10 }.toList()

        assertEquals(3, results.size)
        assertEquals(10, results[0].getOrNull())
        assertTrue(results[1].isFailure)
        assertEquals("Error", results[1].exceptionOrNull()?.message)
        assertEquals(30, results[2].getOrNull())
    }

    @Test
    fun test_mapResult_stringToInt_transformsCorrectly() = runBlocking {
        val flow = flowOf("hello", "world").asResult()

        val results = flow.mapResult { it.length }.toList()

        assertEquals(2, results.size)
        assertEquals(5, results[0].getOrNull())
        assertEquals(5, results[1].getOrNull())
    }

    @Test
    fun test_mapResult_emptyFlow_returnsEmptyList() = runBlocking {
        val flow = flowOf<Result<Int>>()

        val results = flow.mapResult { it * 2 }.toList()

        assertTrue(results.isEmpty())
    }
}
