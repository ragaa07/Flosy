package com.flosy.core.common.util

/**
 * Utility object for formatting currency values.
 *
 * CRITICAL: All currency values are stored and calculated in piasters (Long).
 * 1 EGP = 100 piasters
 *
 * NEVER use Double or Float for money calculations - this causes precision issues.
 *
 * Example:
 * - 8500000L piasters = 85,000 EGP
 * - 15050L piasters = 150.50 EGP
 */
object CurrencyFormatter {

    private const val PIASTERS_PER_POUND = 100L
    private const val CURRENCY_SUFFIX = " EGP"

    /**
     * Formats an amount in piasters to EGP display format with thousands separator.
     *
     * Examples:
     * - 8500000L → "85,000 EGP"
     * - 0L → "0 EGP"
     * - -8500000L → "-85,000 EGP"
     * - 100L → "1 EGP"
     * - 50L → "0 EGP" (rounds down)
     *
     * @param amountInPiasters The amount in piasters (1/100 of EGP)
     * @return Formatted string with thousands separator and EGP suffix
     */
    fun formatEgp(amountInPiasters: Long): String {
        val pounds = piastersToPounds(amountInPiasters)
        val formatted = formatWithThousandsSeparator(pounds)
        return "$formatted$CURRENCY_SUFFIX"
    }

    /**
     * Formats an amount in piasters to EGP display format with decimals.
     *
     * Examples:
     * - 8500000L → "85,000.00 EGP"
     * - 8500050L → "85,000.50 EGP"
     * - 0L → "0.00 EGP"
     * - -8500050L → "-85,000.50 EGP"
     *
     * @param amountInPiasters The amount in piasters (1/100 of EGP)
     * @return Formatted string with decimals, thousands separator and EGP suffix
     */
    fun formatEgpWithDecimals(amountInPiasters: Long): String {
        val isNegative = amountInPiasters < 0
        val absAmount = if (isNegative) -amountInPiasters else amountInPiasters

        val pounds = absAmount / PIASTERS_PER_POUND
        val piastersPart = absAmount % PIASTERS_PER_POUND

        val formattedPounds = formatWithThousandsSeparator(pounds)
        val formattedPiasters = piastersPart.toString().padStart(2, '0')

        val sign = if (isNegative) "-" else ""
        return "$sign$formattedPounds.$formattedPiasters$CURRENCY_SUFFIX"
    }

    /**
     * Converts piasters to pounds (integer division).
     *
     * Example: 8500050L → 85000L
     *
     * @param piasters The amount in piasters
     * @return The amount in whole pounds (fractional piasters are discarded)
     */
    fun piastersToPounds(piasters: Long): Long {
        return piasters / PIASTERS_PER_POUND
    }

    /**
     * Converts pounds to piasters.
     *
     * Example: 85000L → 8500000L
     *
     * @param pounds The amount in whole pounds
     * @return The amount in piasters
     */
    fun poundsToPiasters(pounds: Long): Long {
        return pounds * PIASTERS_PER_POUND
    }

    /**
     * Formats a number with thousands separator (comma).
     *
     * Examples:
     * - 1000 → "1,000"
     * - 1000000 → "1,000,000"
     * - -1000 → "-1,000"
     * - 0 → "0"
     */
    private fun formatWithThousandsSeparator(value: Long): String {
        if (value == 0L) return "0"

        val isNegative = value < 0
        val absValue = if (isNegative) -value else value

        val digits = absValue.toString()
        val result = StringBuilder()

        digits.forEachIndexed { index, char ->
            if (index > 0 && (digits.length - index) % 3 == 0) {
                result.append(',')
            }
            result.append(char)
        }

        return if (isNegative) "-$result" else result.toString()
    }
}
