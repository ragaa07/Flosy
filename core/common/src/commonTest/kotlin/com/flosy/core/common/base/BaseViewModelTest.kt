package com.flosy.core.common.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // ==================== Test ViewModel Implementation ====================

    /**
     * Test implementation of BaseViewModel that exposes protected methods
     * and tracks errors for verification.
     */
    private class TestViewModel : BaseViewModel() {
        var lastError: Throwable? = null
            private set

        var executionCount = 0
            private set

        var executedValues = mutableListOf<String>()
            private set

        override fun handleError(error: Throwable) {
            lastError = error
        }

        fun executeTask(value: String) {
            launchInViewModelScope {
                executionCount++
                executedValues.add(value)
            }
        }

        fun executeTaskWithDelay(value: String, delayMs: Long) {
            launchInViewModelScope {
                delay(delayMs)
                executionCount++
                executedValues.add(value)
            }
        }

        fun executeTaskThatThrows(errorMessage: String) {
            launchInViewModelScope {
                throw RuntimeException(errorMessage)
            }
        }

        fun executeTaskThatThrowsAfterWork(value: String, errorMessage: String) {
            launchInViewModelScope {
                executionCount++
                executedValues.add(value)
                throw IllegalStateException(errorMessage)
            }
        }
    }

    // ==================== launchInViewModelScope Tests ====================

    @Test
    fun test_launchInViewModelScope_executesBlock() = runTest {
        val viewModel = TestViewModel()

        viewModel.executeTask("test1")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(1, viewModel.executionCount)
        assertEquals(listOf("test1"), viewModel.executedValues)
    }

    @Test
    fun test_launchInViewModelScope_multipleExecutions_allExecute() = runTest {
        val viewModel = TestViewModel()

        viewModel.executeTask("first")
        viewModel.executeTask("second")
        viewModel.executeTask("third")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(3, viewModel.executionCount)
        assertTrue(viewModel.executedValues.contains("first"))
        assertTrue(viewModel.executedValues.contains("second"))
        assertTrue(viewModel.executedValues.contains("third"))
    }

    @Test
    fun test_launchInViewModelScope_withDelay_executesAfterDelay() = runTest {
        val viewModel = TestViewModel()

        viewModel.executeTaskWithDelay("delayed", 1000L)

        // Before advancing time, task should not have executed
        assertEquals(0, viewModel.executionCount)

        // Advance time past the delay
        testDispatcher.scheduler.advanceTimeBy(1001L)
        testDispatcher.scheduler.runCurrent()

        assertEquals(1, viewModel.executionCount)
        assertEquals(listOf("delayed"), viewModel.executedValues)
    }

    // ==================== handleError Tests ====================

    @Test
    fun test_launchInViewModelScope_exceptionThrown_callsHandleError() = runTest {
        val viewModel = TestViewModel()

        viewModel.executeTaskThatThrows("Test exception")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals("Test exception", viewModel.lastError?.message)
        assertTrue(viewModel.lastError is RuntimeException)
    }

    @Test
    fun test_launchInViewModelScope_exceptionAfterWork_workCompletesAndErrorHandled() = runTest {
        val viewModel = TestViewModel()

        viewModel.executeTaskThatThrowsAfterWork("completed", "Error after work")
        testDispatcher.scheduler.advanceUntilIdle()

        // Work should have been done before the exception
        assertEquals(1, viewModel.executionCount)
        assertEquals(listOf("completed"), viewModel.executedValues)

        // Error should have been captured
        assertEquals("Error after work", viewModel.lastError?.message)
        assertTrue(viewModel.lastError is IllegalStateException)
    }

    @Test
    fun test_launchInViewModelScope_noException_handleErrorNotCalled() = runTest {
        val viewModel = TestViewModel()

        viewModel.executeTask("success")
        testDispatcher.scheduler.advanceUntilIdle()

        assertNull(viewModel.lastError)
    }

    @Test
    fun test_launchInViewModelScope_multipleExceptions_lastErrorCaptured() = runTest {
        val viewModel = TestViewModel()

        viewModel.executeTaskThatThrows("First error")
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.executeTaskThatThrows("Second error")
        testDispatcher.scheduler.advanceUntilIdle()

        // Last error should be captured
        assertEquals("Second error", viewModel.lastError?.message)
    }

    // ==================== Default handleError Behavior ====================

    @Test
    fun test_defaultHandleError_doesNotCrash() = runTest {
        // Test with a ViewModel that doesn't override handleError
        val viewModel = object : BaseViewModel() {
            fun throwError() {
                launchInViewModelScope {
                    throw RuntimeException("Unhandled error")
                }
            }
        }

        // Should not throw - default implementation silently handles error
        viewModel.throwError()
        testDispatcher.scheduler.advanceUntilIdle()

        // Test passes if no exception is thrown
    }
}
