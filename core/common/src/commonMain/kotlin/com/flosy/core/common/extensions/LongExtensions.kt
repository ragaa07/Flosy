package com.flosy.core.common.extensions

import com.flosy.core.common.util.CurrencyFormatter

/**
 * Extension functions for Long values, primarily for currency operations.
 *
 * CRITICAL: All currency values are stored and calculated in piasters (Long).
 * 1 EGP = 100 piasters
 */

private const val PIASTERS_PER_POUND = 100L

/**
 * Converts EGP (pounds) value to piasters.
 *
 * Example: 850L.toPiasters() = 85000L (850 EGP = 85,000 piasters)
 *
 * @return The amount in piasters
 * @throws IllegalArgumentException if the value is negative
 */
fun Long.toPiasters(): Long {
    require(this >= 0) { "Currency value cannot be negative: $this" }
    return this * PIASTERS_PER_POUND
}

/**
 * Formats this Long (assumed to be in piasters) to a display currency string.
 *
 * Example: 8500000L.toDisplayCurrency() = "85,000 EGP"
 *
 * @return Formatted currency string
 */
fun Long.toDisplayCurrency(): String {
    return CurrencyFormatter.formatEgp(this)
}

/**
 * Formats this Long (assumed to be in piasters) to a display currency string with decimals.
 *
 * Example: 8500050L.toDisplayCurrencyWithDecimals() = "85,000.50 EGP"
 *
 * @return Formatted currency string with decimals
 */
fun Long.toDisplayCurrencyWithDecimals(): String {
    return CurrencyFormatter.formatEgpWithDecimals(this)
}
