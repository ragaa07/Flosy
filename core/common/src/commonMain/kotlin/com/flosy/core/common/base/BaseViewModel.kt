package com.flosy.core.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Base ViewModel class that provides common functionality for all ViewModels in the app.
 *
 * Features:
 * - Provides [viewModelScope] for coroutine operations (inherited from ViewModel)
 * - Standard [launchInViewModelScope] helper with error handling
 * - Overridable [handleError] for custom error handling in subclasses
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * Launches a coroutine in [viewModelScope] with standard error handling.
     *
     * Any uncaught exceptions will be passed to [handleError].
     *
     * @param block The suspend function to execute
     */
    protected fun launchInViewModelScope(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    /**
     * Handles errors that occur during coroutine execution.
     *
     * Subclasses can override this to provide custom error handling behavior,
     * such as updating a UI state with error information.
     *
     * Default implementation does nothing - subclasses should override
     * to handle errors appropriately for their use case.
     *
     * @param error The exception that occurred
     */
    protected open fun handleError(error: Throwable) {
        // Default implementation - subclasses can override
    }
}
