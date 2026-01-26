package com.flosy.core.common.util

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

/**
 * Utility object for date and time operations using kotlinx-datetime.
 *
 * IMPORTANT: Always use kotlinx.datetime, NOT java.time - this ensures cross-platform compatibility.
 *
 * Payday Conventions:
 * - Payday values: 1-31 represent the day of month
 * - Payday 0: Special value meaning "last day of month"
 * - If payday > days in month (e.g., 31 in April), uses last day of that month
 */
object DateTimeUtil {

    /**
     * Returns the current date in the system's default timezone.
     */
    fun getCurrentDate(): LocalDate {
        return Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
    }

    /**
     * Returns the current timestamp in epoch milliseconds.
     */
    fun getCurrentTimestamp(): Long {
        return Clock.System.now().toEpochMilliseconds()
    }

    /**
     * Calculates the number of days until the next occurrence of payday.
     *
     * @param payday The day of month for payday (1-31), or 0 for last day of month
     * @return Number of days until payday (0 if today is payday)
     * @throws IllegalArgumentException if payday is not in range 0-31
     *
     * Examples:
     * - If today is Jan 15 and payday is 25, returns 10
     * - If today is Jan 25 and payday is 25, returns 0
     * - If today is Jan 26 and payday is 25, returns days until Feb 25
     * - If payday is 31 and current month has 30 days, uses 30th
     */
    fun daysUntilPayday(payday: Int): Int {
        validatePayday(payday)
        val today = getCurrentDate()
        return daysUntilPayday(payday, today)
    }

    /**
     * Calculates the number of days until the next occurrence of payday from a given date.
     *
     * @param payday The day of month for payday (1-31), or 0 for last day of month
     * @param fromDate The date to calculate from
     * @return Number of days until payday (0 if fromDate is payday)
     * @throws IllegalArgumentException if payday is not in range 0-31
     */
    fun daysUntilPayday(payday: Int, fromDate: LocalDate): Int {
        validatePayday(payday)
        // Get payday for current month
        val currentMonthPayday = getPaydayForMonth(fromDate.year, fromDate.month, payday)

        return if (fromDate.dayOfMonth <= currentMonthPayday.dayOfMonth &&
            fromDate.month == currentMonthPayday.month &&
            fromDate.year == currentMonthPayday.year
        ) {
            // Payday is in current month and hasn't passed yet (or is today)
            fromDate.daysUntil(currentMonthPayday)
        } else {
            // Payday has passed, calculate for next month
            val nextMonth = fromDate.plus(1, DateTimeUnit.MONTH)
            val nextMonthPayday = getPaydayForMonth(
                nextMonth.year,
                nextMonth.month,
                payday
            )
            fromDate.daysUntil(nextMonthPayday)
        }
    }

    /**
     * Checks if today is payday.
     *
     * @param payday The day of month for payday (1-31), or 0 for last day of month
     * @return true if today is payday
     * @throws IllegalArgumentException if payday is not in range 0-31
     */
    fun isPayday(payday: Int): Boolean {
        validatePayday(payday)
        return daysUntilPayday(payday) == 0
    }

    /**
     * Checks if a specific date is payday.
     *
     * @param payday The day of month for payday (1-31), or 0 for last day of month
     * @param date The date to check
     * @return true if the date is payday
     * @throws IllegalArgumentException if payday is not in range 0-31
     */
    fun isPayday(payday: Int, date: LocalDate): Boolean {
        validatePayday(payday)
        val paydayForMonth = getPaydayForMonth(date.year, date.month, payday)
        return date == paydayForMonth
    }

    /**
     * Gets the actual payday date for a specific month, handling edge cases.
     *
     * Handles:
     * - Payday 0 → last day of month
     * - Payday > days in month → last day of month
     * - Payday 31 in February → Feb 28/29
     *
     * @param year The year
     * @param month The month
     * @param payday The day of month for payday (1-31), or 0 for last day of month
     * @return The actual LocalDate of payday for that month
     * @throws IllegalArgumentException if payday is not in range 0-31
     */
    fun getPaydayForMonth(year: Int, month: Month, payday: Int): LocalDate {
        validatePayday(payday)
        val daysInMonth = getDaysInMonth(year, month)

        val actualDay = when {
            payday == 0 -> daysInMonth // Last day of month
            payday > daysInMonth -> daysInMonth // Clamp to last day
            else -> payday
        }

        return LocalDate(year, month, actualDay)
    }

    /**
     * Validates that payday is within the acceptable range.
     *
     * @param payday The payday value to validate
     * @throws IllegalArgumentException if payday is not in range 0-31
     */
    private fun validatePayday(payday: Int) {
        require(payday in 0..31) {
            "Payday must be between 0 and 31, but was $payday"
        }
    }

    /**
     * Returns the number of days in a given month.
     *
     * Properly handles leap years for February.
     */
    private fun getDaysInMonth(year: Int, month: Month): Int {
        return when (month) {
            Month.JANUARY -> 31
            Month.FEBRUARY -> if (isLeapYear(year)) 29 else 28
            Month.MARCH -> 31
            Month.APRIL -> 30
            Month.MAY -> 31
            Month.JUNE -> 30
            Month.JULY -> 31
            Month.AUGUST -> 31
            Month.SEPTEMBER -> 30
            Month.OCTOBER -> 31
            Month.NOVEMBER -> 30
            Month.DECEMBER -> 31
            else -> 30 // Should never happen
        }
    }

    /**
     * Checks if a year is a leap year.
     */
    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}
