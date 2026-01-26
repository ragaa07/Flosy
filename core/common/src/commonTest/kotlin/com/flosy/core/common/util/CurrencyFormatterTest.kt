package com.flosy.core.common.util

import kotlin.test.Test
import kotlin.test.assertEquals

class CurrencyFormatterTest {

    // ==================== formatEgp Tests ====================

    @Test
    fun test_formatEgp_positiveValue_returnsFormattedString() {
        val result = CurrencyFormatter.formatEgp(8500000L)
        assertEquals("85,000 EGP", result)
    }

    @Test
    fun test_formatEgp_zero_returnsZeroEgp() {
        val result = CurrencyFormatter.formatEgp(0L)
        assertEquals("0 EGP", result)
    }

    @Test
    fun test_formatEgp_negativeValue_returnsNegativeFormattedString() {
        val result = CurrencyFormatter.formatEgp(-8500000L)
        assertEquals("-85,000 EGP", result)
    }

    @Test
    fun test_formatEgp_oneHundredPiasters_returnsOneEgp() {
        val result = CurrencyFormatter.formatEgp(100L)
        assertEquals("1 EGP", result)
    }

    @Test
    fun test_formatEgp_fiftyPiasters_roundsDownToZero() {
        val result = CurrencyFormatter.formatEgp(50L)
        assertEquals("0 EGP", result)
    }

    @Test
    fun test_formatEgp_oneThousand_noCommaSeparator() {
        val result = CurrencyFormatter.formatEgp(100000L) // 1000 EGP
        assertEquals("1,000 EGP", result)
    }

    @Test
    fun test_formatEgp_tenThousand_hasCommaSeparator() {
        val result = CurrencyFormatter.formatEgp(1000000L) // 10,000 EGP
        assertEquals("10,000 EGP", result)
    }

    @Test
    fun test_formatEgp_hundredThousand_hasCommaSeparator() {
        val result = CurrencyFormatter.formatEgp(10000000L) // 100,000 EGP
        assertEquals("100,000 EGP", result)
    }

    @Test
    fun test_formatEgp_oneMillion_hasTwoCommaSeparators() {
        val result = CurrencyFormatter.formatEgp(100000000L) // 1,000,000 EGP
        assertEquals("1,000,000 EGP", result)
    }

    @Test
    fun test_formatEgp_smallValue_noCommaSeparator() {
        val result = CurrencyFormatter.formatEgp(10000L) // 100 EGP
        assertEquals("100 EGP", result)
    }

    // ==================== formatEgpWithDecimals Tests ====================

    @Test
    fun test_formatEgpWithDecimals_positiveValueNoDecimals_returnsDoubleZeroDecimals() {
        val result = CurrencyFormatter.formatEgpWithDecimals(8500000L)
        assertEquals("85,000.00 EGP", result)
    }

    @Test
    fun test_formatEgpWithDecimals_positiveValueWithDecimals_returnsCorrectDecimals() {
        val result = CurrencyFormatter.formatEgpWithDecimals(8500050L)
        assertEquals("85,000.50 EGP", result)
    }

    @Test
    fun test_formatEgpWithDecimals_zero_returnsZeroWithDecimals() {
        val result = CurrencyFormatter.formatEgpWithDecimals(0L)
        assertEquals("0.00 EGP", result)
    }

    @Test
    fun test_formatEgpWithDecimals_negativeValue_returnsNegativeWithDecimals() {
        val result = CurrencyFormatter.formatEgpWithDecimals(-8500050L)
        assertEquals("-85,000.50 EGP", result)
    }

    @Test
    fun test_formatEgpWithDecimals_singleDigitPiasters_padsWithZero() {
        val result = CurrencyFormatter.formatEgpWithDecimals(105L) // 1.05 EGP
        assertEquals("1.05 EGP", result)
    }

    @Test
    fun test_formatEgpWithDecimals_onlyPiasters_showsZeroPounds() {
        val result = CurrencyFormatter.formatEgpWithDecimals(50L) // 0.50 EGP
        assertEquals("0.50 EGP", result)
    }

    // ==================== piastersToPounds Tests ====================

    @Test
    fun test_piastersToPounds_standardConversion_returnsCorrectPounds() {
        val result = CurrencyFormatter.piastersToPounds(8500000L)
        assertEquals(85000L, result)
    }

    @Test
    fun test_piastersToPounds_withFractionalPiasters_truncatesFraction() {
        val result = CurrencyFormatter.piastersToPounds(8500050L)
        assertEquals(85000L, result)
    }

    @Test
    fun test_piastersToPounds_zero_returnsZero() {
        val result = CurrencyFormatter.piastersToPounds(0L)
        assertEquals(0L, result)
    }

    @Test
    fun test_piastersToPounds_negativePiasters_returnsNegativePounds() {
        val result = CurrencyFormatter.piastersToPounds(-8500000L)
        assertEquals(-85000L, result)
    }

    @Test
    fun test_piastersToPounds_lessThanHundred_returnsZero() {
        val result = CurrencyFormatter.piastersToPounds(99L)
        assertEquals(0L, result)
    }

    // ==================== poundsToPiasters Tests ====================

    @Test
    fun test_poundsToPiasters_standardConversion_returnsCorrectPiasters() {
        val result = CurrencyFormatter.poundsToPiasters(85000L)
        assertEquals(8500000L, result)
    }

    @Test
    fun test_poundsToPiasters_zero_returnsZero() {
        val result = CurrencyFormatter.poundsToPiasters(0L)
        assertEquals(0L, result)
    }

    @Test
    fun test_poundsToPiasters_negativePounds_returnsNegativePiasters() {
        val result = CurrencyFormatter.poundsToPiasters(-85000L)
        assertEquals(-8500000L, result)
    }

    @Test
    fun test_poundsToPiasters_one_returnsHundred() {
        val result = CurrencyFormatter.poundsToPiasters(1L)
        assertEquals(100L, result)
    }

    // ==================== Round-trip Tests ====================

    @Test
    fun test_roundTrip_poundsToPiastersToPounds_preservesValue() {
        val original = 12345L
        val piasters = CurrencyFormatter.poundsToPiasters(original)
        val result = CurrencyFormatter.piastersToPounds(piasters)
        assertEquals(original, result)
    }
}
