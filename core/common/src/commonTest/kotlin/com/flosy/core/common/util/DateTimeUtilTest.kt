package com.flosy.core.common.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DateTimeUtilTest {

    // ==================== daysUntilPayday Tests ====================

    @Test
    fun test_daysUntilPayday_paydayLaterInCurrentMonth_returnsDaysUntil() {
        // Jan 15, payday is 25 → 10 days
        val fromDate = LocalDate(2026, Month.JANUARY, 15)
        val result = DateTimeUtil.daysUntilPayday(25, fromDate)
        assertEquals(10, result)
    }

    @Test
    fun test_daysUntilPayday_paydayHasPassed_returnsDaysUntilNextMonth() {
        // Jan 26, payday is 25 → days until Feb 25
        val fromDate = LocalDate(2026, Month.JANUARY, 26)
        val result = DateTimeUtil.daysUntilPayday(25, fromDate)
        assertEquals(30, result) // Jan 26 to Feb 25 = 30 days
    }

    @Test
    fun test_daysUntilPayday_todayIsPayday_returnsZero() {
        // Jan 25, payday is 25 → 0 days
        val fromDate = LocalDate(2026, Month.JANUARY, 25)
        val result = DateTimeUtil.daysUntilPayday(25, fromDate)
        assertEquals(0, result)
    }

    @Test
    fun test_daysUntilPayday_payday31InThirtyDayMonth_usesLastDayOfMonth() {
        // April 15, payday 31 → April only has 30 days, so payday is 30th
        val fromDate = LocalDate(2026, Month.APRIL, 15)
        val result = DateTimeUtil.daysUntilPayday(31, fromDate)
        assertEquals(15, result) // April 15 to April 30 = 15 days
    }

    @Test
    fun test_daysUntilPayday_payday31InFebruary_usesLastDayOfFebruary() {
        // Feb 15 2026 (not leap year), payday 31 → Feb has 28 days
        val fromDate = LocalDate(2026, Month.FEBRUARY, 15)
        val result = DateTimeUtil.daysUntilPayday(31, fromDate)
        assertEquals(13, result) // Feb 15 to Feb 28 = 13 days
    }

    @Test
    fun test_daysUntilPayday_payday31InFebruaryLeapYear_usesLastDay() {
        // Feb 15 2024 (leap year), payday 31 → Feb has 29 days
        val fromDate = LocalDate(2024, Month.FEBRUARY, 15)
        val result = DateTimeUtil.daysUntilPayday(31, fromDate)
        assertEquals(14, result) // Feb 15 to Feb 29 = 14 days
    }

    @Test
    fun test_daysUntilPayday_payday0_usesLastDayOfMonth() {
        // Jan 15, payday 0 → last day (Jan 31)
        val fromDate = LocalDate(2026, Month.JANUARY, 15)
        val result = DateTimeUtil.daysUntilPayday(0, fromDate)
        assertEquals(16, result) // Jan 15 to Jan 31 = 16 days
    }

    @Test
    fun test_daysUntilPayday_payday0InApril_usesApril30() {
        // April 15, payday 0 → last day (April 30)
        val fromDate = LocalDate(2026, Month.APRIL, 15)
        val result = DateTimeUtil.daysUntilPayday(0, fromDate)
        assertEquals(15, result) // April 15 to April 30 = 15 days
    }

    @Test
    fun test_daysUntilPayday_payday0InFebruary_usesLastDayOfFebruary() {
        // Feb 15, payday 0 → Feb 28
        val fromDate = LocalDate(2026, Month.FEBRUARY, 15)
        val result = DateTimeUtil.daysUntilPayday(0, fromDate)
        assertEquals(13, result) // Feb 15 to Feb 28 = 13 days
    }

    @Test
    fun test_daysUntilPayday_payday1stAndAlreadyPassed_returnsToNextMonth() {
        // Jan 15, payday is 1 → days until Feb 1
        val fromDate = LocalDate(2026, Month.JANUARY, 15)
        val result = DateTimeUtil.daysUntilPayday(1, fromDate)
        assertEquals(17, result) // Jan 15 to Feb 1 = 17 days
    }

    @Test
    fun test_daysUntilPayday_payday1st_todayIs1st_returnsZero() {
        // Jan 1, payday is 1 → 0 days
        val fromDate = LocalDate(2026, Month.JANUARY, 1)
        val result = DateTimeUtil.daysUntilPayday(1, fromDate)
        assertEquals(0, result)
    }

    // ==================== isPayday Tests ====================

    @Test
    fun test_isPayday_todayIsPayday_returnsTrue() {
        val date = LocalDate(2026, Month.JANUARY, 25)
        val result = DateTimeUtil.isPayday(25, date)
        assertTrue(result)
    }

    @Test
    fun test_isPayday_todayIsNotPayday_returnsFalse() {
        val date = LocalDate(2026, Month.JANUARY, 24)
        val result = DateTimeUtil.isPayday(25, date)
        assertFalse(result)
    }

    @Test
    fun test_isPayday_payday31InApril_april30IsPayday() {
        val date = LocalDate(2026, Month.APRIL, 30)
        val result = DateTimeUtil.isPayday(31, date)
        assertTrue(result)
    }

    @Test
    fun test_isPayday_payday31InApril_april29IsNotPayday() {
        val date = LocalDate(2026, Month.APRIL, 29)
        val result = DateTimeUtil.isPayday(31, date)
        assertFalse(result)
    }

    @Test
    fun test_isPayday_payday0_lastDayOfMonthIsPayday() {
        val date = LocalDate(2026, Month.JANUARY, 31)
        val result = DateTimeUtil.isPayday(0, date)
        assertTrue(result)
    }

    @Test
    fun test_isPayday_payday0InFebruary_feb28IsPayday() {
        val date = LocalDate(2026, Month.FEBRUARY, 28)
        val result = DateTimeUtil.isPayday(0, date)
        assertTrue(result)
    }

    // ==================== getPaydayForMonth Tests ====================

    @Test
    fun test_getPaydayForMonth_normalPayday_returnsExactDay() {
        val result = DateTimeUtil.getPaydayForMonth(2026, Month.JANUARY, 25)
        assertEquals(LocalDate(2026, Month.JANUARY, 25), result)
    }

    @Test
    fun test_getPaydayForMonth_payday31InApril_returns30() {
        val result = DateTimeUtil.getPaydayForMonth(2026, Month.APRIL, 31)
        assertEquals(LocalDate(2026, Month.APRIL, 30), result)
    }

    @Test
    fun test_getPaydayForMonth_payday31InFebruary_returns28() {
        val result = DateTimeUtil.getPaydayForMonth(2026, Month.FEBRUARY, 31)
        assertEquals(LocalDate(2026, Month.FEBRUARY, 28), result)
    }

    @Test
    fun test_getPaydayForMonth_payday31InFebruaryLeapYear_returns29() {
        val result = DateTimeUtil.getPaydayForMonth(2024, Month.FEBRUARY, 31)
        assertEquals(LocalDate(2024, Month.FEBRUARY, 29), result)
    }

    @Test
    fun test_getPaydayForMonth_payday30InFebruary_returns28() {
        val result = DateTimeUtil.getPaydayForMonth(2026, Month.FEBRUARY, 30)
        assertEquals(LocalDate(2026, Month.FEBRUARY, 28), result)
    }

    @Test
    fun test_getPaydayForMonth_payday29InFebruary_returns28() {
        val result = DateTimeUtil.getPaydayForMonth(2026, Month.FEBRUARY, 29)
        assertEquals(LocalDate(2026, Month.FEBRUARY, 28), result)
    }

    @Test
    fun test_getPaydayForMonth_payday29InFebruaryLeapYear_returns29() {
        val result = DateTimeUtil.getPaydayForMonth(2024, Month.FEBRUARY, 29)
        assertEquals(LocalDate(2024, Month.FEBRUARY, 29), result)
    }

    @Test
    fun test_getPaydayForMonth_payday0_returnsLastDayOfMonth() {
        val result = DateTimeUtil.getPaydayForMonth(2026, Month.JANUARY, 0)
        assertEquals(LocalDate(2026, Month.JANUARY, 31), result)
    }

    @Test
    fun test_getPaydayForMonth_payday0InApril_returnsApril30() {
        val result = DateTimeUtil.getPaydayForMonth(2026, Month.APRIL, 0)
        assertEquals(LocalDate(2026, Month.APRIL, 30), result)
    }

    @Test
    fun test_getPaydayForMonth_payday0InFebruary_returnsFeb28() {
        val result = DateTimeUtil.getPaydayForMonth(2026, Month.FEBRUARY, 0)
        assertEquals(LocalDate(2026, Month.FEBRUARY, 28), result)
    }

    @Test
    fun test_getPaydayForMonth_payday0InFebruaryLeapYear_returnsFeb29() {
        val result = DateTimeUtil.getPaydayForMonth(2024, Month.FEBRUARY, 0)
        assertEquals(LocalDate(2024, Month.FEBRUARY, 29), result)
    }

    @Test
    fun test_getPaydayForMonth_payday1_returnsFirst() {
        val result = DateTimeUtil.getPaydayForMonth(2026, Month.MARCH, 1)
        assertEquals(LocalDate(2026, Month.MARCH, 1), result)
    }

    // ==================== Edge Cases ====================

    @Test
    fun test_daysUntilPayday_crossingYearBoundary_calculatesCorrectly() {
        // Dec 28, payday is 5 → days until Jan 5
        val fromDate = LocalDate(2025, Month.DECEMBER, 28)
        val result = DateTimeUtil.daysUntilPayday(5, fromDate)
        assertEquals(8, result) // Dec 28 to Jan 5 = 8 days
    }

    @Test
    fun test_daysUntilPayday_lastDayOfDecember_paydayAlreadyPassed() {
        // Dec 31, payday is 25 → days until Jan 25
        val fromDate = LocalDate(2025, Month.DECEMBER, 31)
        val result = DateTimeUtil.daysUntilPayday(25, fromDate)
        assertEquals(25, result) // Dec 31 to Jan 25 = 25 days
    }

    // ==================== Payday Validation Tests ====================

    @Test
    fun test_daysUntilPayday_negativePayday_throwsException() {
        val fromDate = LocalDate(2026, Month.JANUARY, 15)
        assertFailsWith<IllegalArgumentException> {
            DateTimeUtil.daysUntilPayday(-1, fromDate)
        }
    }

    @Test
    fun test_daysUntilPayday_paydayOver31_throwsException() {
        val fromDate = LocalDate(2026, Month.JANUARY, 15)
        assertFailsWith<IllegalArgumentException> {
            DateTimeUtil.daysUntilPayday(32, fromDate)
        }
    }

    @Test
    fun test_isPayday_negativePayday_throwsException() {
        val date = LocalDate(2026, Month.JANUARY, 15)
        assertFailsWith<IllegalArgumentException> {
            DateTimeUtil.isPayday(-5, date)
        }
    }

    @Test
    fun test_getPaydayForMonth_negativePayday_throwsException() {
        assertFailsWith<IllegalArgumentException> {
            DateTimeUtil.getPaydayForMonth(2026, Month.JANUARY, -1)
        }
    }

    @Test
    fun test_getPaydayForMonth_payday100_throwsException() {
        assertFailsWith<IllegalArgumentException> {
            DateTimeUtil.getPaydayForMonth(2026, Month.JANUARY, 100)
        }
    }
}
