package com.flosy.core.common.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Extension functions for Kotlin Flow, providing utilities for Result wrapping
 * and common transformations.
 *
 * Uses Kotlin's built-in [Result] class.
 */

/**
 * Wraps each emission from this Flow in Kotlin's [Result].
 *
 * - Successful emissions are wrapped in [Result.success]
 * - Exceptions are caught and wrapped in [Result.failure]
 *
 * Example:
 * ```
 * repository.getItems()
 *     .asResult()
 *     .collect { result ->
 *         result.onSuccess { data -> updateUI(data) }
 *               .onFailure { error -> showError(error.message) }
 *     }
 * ```
 *
 * @return A Flow that emits Result-wrapped values
 */
fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map { Result.success(it) }
        .catch { e -> emit(Result.failure(e)) }
}

/**
 * Maps each successful result to a new value using the given transform.
 *
 * If the current result is a failure, it is passed through unchanged.
 *
 * @param transform The function to transform the success value
 * @return A Flow with transformed results
 */
fun <T, R> Flow<Result<T>>.mapResult(transform: (T) -> R): Flow<Result<R>> {
    return this.map { result ->
        result.map(transform)
    }
}
