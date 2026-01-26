package com.flosy.core.common.extensions

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LongExtensionsTest {

    // ==================== toPiasters Tests ====================

    @Test
    fun test_toPiasters_positiveValue_returnsMultipliedBy100() {
        val result = 850L.toPiasters()
        assertEquals(85000L, result)
    }

    @Test
    fun test_toPiasters_zero_returnsZero() {
        val result = 0L.toPiasters()
        assertEquals(0L, result)
    }

    @Test
    fun test_toPiasters_one_returns100() {
        val result = 1L.toPiasters()
        assertEquals(100L, result)
    }

    @Test
    fun test_toPiasters_largeValue_returnsCorrectPiasters() {
        val result = 100000L.toPiasters() // 100,000 EGP
        assertEquals(10000000L, result)
    }

    @Test
    fun test_toPiasters_negativeValue_throwsException() {
        assertFailsWith<IllegalArgumentException> {
            (-100L).toPiasters()
        }
    }

    @Test
    fun test_toPiasters_negativeOne_throwsException() {
        assertFailsWith<IllegalArgumentException> {
            (-1L).toPiasters()
        }
    }

    // ==================== toDisplayCurrency Tests ====================

    @Test
    fun test_toDisplayCurrency_standardValue_returnsFormattedString() {
        val result = 8500000L.toDisplayCurrency()
        assertEquals("85,000 EGP", result)
    }

    @Test
    fun test_toDisplayCurrency_zero_returnsZeroEgp() {
        val result = 0L.toDisplayCurrency()
        assertEquals("0 EGP", result)
    }

    @Test
    fun test_toDisplayCurrency_negativeValue_returnsNegativeFormatted() {
        val result = (-8500000L).toDisplayCurrency()
        assertEquals("-85,000 EGP", result)
    }

    @Test
    fun test_toDisplayCurrency_smallValue_returnsFormattedString() {
        val result = 100L.toDisplayCurrency() // 1 EGP
        assertEquals("1 EGP", result)
    }

    @Test
    fun test_toDisplayCurrency_largeValue_hasThousandsSeparators() {
        val result = 100000000L.toDisplayCurrency() // 1,000,000 EGP
        assertEquals("1,000,000 EGP", result)
    }

    // ==================== toDisplayCurrencyWithDecimals Tests ====================

    @Test
    fun test_toDisplayCurrencyWithDecimals_wholeAmount_returnsDoubleZeroDecimals() {
        val result = 8500000L.toDisplayCurrencyWithDecimals()
        assertEquals("85,000.00 EGP", result)
    }

    @Test
    fun test_toDisplayCurrencyWithDecimals_withPiasters_returnsCorrectDecimals() {
        val result = 8500050L.toDisplayCurrencyWithDecimals()
        assertEquals("85,000.50 EGP", result)
    }

    @Test
    fun test_toDisplayCurrencyWithDecimals_zero_returnsZeroWithDecimals() {
        val result = 0L.toDisplayCurrencyWithDecimals()
        assertEquals("0.00 EGP", result)
    }

    @Test
    fun test_toDisplayCurrencyWithDecimals_negativeValue_returnsNegativeWithDecimals() {
        val result = (-8500050L).toDisplayCurrencyWithDecimals()
        assertEquals("-85,000.50 EGP", result)
    }

    @Test
    fun test_toDisplayCurrencyWithDecimals_singleDigitPiasters_padsWithZero() {
        val result = 105L.toDisplayCurrencyWithDecimals() // 1.05 EGP
        assertEquals("1.05 EGP", result)
    }

    @Test
    fun test_toDisplayCurrencyWithDecimals_onlyPiasters_showsZeroPounds() {
        val result = 50L.toDisplayCurrencyWithDecimals() // 0.50 EGP
        assertEquals("0.50 EGP", result)
    }
}
