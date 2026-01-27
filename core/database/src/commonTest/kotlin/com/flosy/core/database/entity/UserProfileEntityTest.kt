package com.flosy.core.database.entity

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertFailsWith

/**
 * Unit tests for UserProfileEntity.
 *
 * These tests verify the data class behavior and ensure proper
 * handling of financial values stored in piasters.
 *
 * Note: DAO tests require an actual database instance and should
 * be written as instrumented tests (androidTest) or integration tests.
 */
class UserProfileEntityTest {

    @Test
    fun test_create_entity_withDefaultId_hasZeroId() {
        val entity = UserProfileEntity(
            salaryInPiasters = 8500000L,
            payday = 27,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals(0L, entity.id)
    }

    @Test
    fun test_create_entity_withExplicitId_preservesId() {
        val entity = UserProfileEntity(
            id = 42L,
            salaryInPiasters = 8500000L,
            payday = 27,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals(42L, entity.id)
    }

    @Test
    fun test_salaryInPiasters_storesCorrectValue() {
        // 85,000 EGP = 8,500,000 piasters
        val entity = UserProfileEntity(
            salaryInPiasters = 8500000L,
            payday = 27,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals(8500000L, entity.salaryInPiasters)
    }

    @Test
    fun test_salaryInPiasters_handlesZeroValue() {
        val entity = UserProfileEntity(
            salaryInPiasters = 0L,
            payday = 1,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals(0L, entity.salaryInPiasters)
    }

    @Test
    fun test_salaryInPiasters_handlesLargeValue() {
        // 1,000,000 EGP = 100,000,000 piasters (Long handles this easily)
        val entity = UserProfileEntity(
            salaryInPiasters = 100000000L,
            payday = 15,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals(100000000L, entity.salaryInPiasters)
    }

    @Test
    fun test_payday_validRange_firstOfMonth() {
        val entity = UserProfileEntity(
            salaryInPiasters = 5000000L,
            payday = 1,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals(1, entity.payday)
    }

    @Test
    fun test_payday_validRange_lastDayOfMonth() {
        val entity = UserProfileEntity(
            salaryInPiasters = 5000000L,
            payday = 31,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals(31, entity.payday)
    }

    @Test
    fun test_payday_specialValue_zeroMeansLastDay() {
        // 0 = special value meaning "last day of month"
        val entity = UserProfileEntity(
            salaryInPiasters = 5000000L,
            payday = 0,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals(0, entity.payday)
    }

    @Test
    fun test_currencyCode_defaultIsEGP() {
        val entity = UserProfileEntity(
            salaryInPiasters = 5000000L,
            payday = 15,
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals("EGP", entity.currencyCode)
    }

    @Test
    fun test_currencyCode_canBeOverridden() {
        val entity = UserProfileEntity(
            salaryInPiasters = 5000000L,
            payday = 15,
            currencyCode = "USD",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals("USD", entity.currencyCode)
    }

    @Test
    fun test_timestamps_storedAsEpochMilliseconds() {
        val createdAt = 1706000000000L // Some timestamp
        val updatedAt = 1706000001000L // 1 second later

        val entity = UserProfileEntity(
            salaryInPiasters = 5000000L,
            payday = 15,
            currencyCode = "EGP",
            createdAt = createdAt,
            updatedAt = updatedAt
        )

        assertEquals(createdAt, entity.createdAt)
        assertEquals(updatedAt, entity.updatedAt)
    }

    @Test
    fun test_equality_sameValues_areEqual() {
        val entity1 = UserProfileEntity(
            id = 1L,
            salaryInPiasters = 8500000L,
            payday = 27,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        val entity2 = UserProfileEntity(
            id = 1L,
            salaryInPiasters = 8500000L,
            payday = 27,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals(entity1, entity2)
    }

    @Test
    fun test_equality_differentId_notEqual() {
        val entity1 = UserProfileEntity(
            id = 1L,
            salaryInPiasters = 8500000L,
            payday = 27,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        val entity2 = UserProfileEntity(
            id = 2L,
            salaryInPiasters = 8500000L,
            payday = 27,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertNotEquals(entity1, entity2)
    }

    @Test
    fun test_copy_changesOnlySpecifiedFields() {
        val original = UserProfileEntity(
            id = 1L,
            salaryInPiasters = 8500000L,
            payday = 27,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        val updated = original.copy(
            salaryInPiasters = 10000000L,
            updatedAt = 1706000001000L
        )

        assertEquals(1L, updated.id)
        assertEquals(10000000L, updated.salaryInPiasters)
        assertEquals(27, updated.payday)
        assertEquals("EGP", updated.currencyCode)
        assertEquals(1706000000000L, updated.createdAt)
        assertEquals(1706000001000L, updated.updatedAt)
    }

    @Test
    fun test_hashCode_sameValues_sameHash() {
        val entity1 = UserProfileEntity(
            id = 1L,
            salaryInPiasters = 8500000L,
            payday = 27,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        val entity2 = UserProfileEntity(
            id = 1L,
            salaryInPiasters = 8500000L,
            payday = 27,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )

        assertEquals(entity1.hashCode(), entity2.hashCode())
    }

    // ==================== VALIDATION TESTS ====================

    @Test
    fun test_salaryInPiasters_negativeValue_throwsException() {
        assertFailsWith<IllegalArgumentException> {
            UserProfileEntity(
                salaryInPiasters = -100L,
                payday = 15,
                currencyCode = "EGP",
                createdAt = 1706000000000L,
                updatedAt = 1706000000000L
            )
        }
    }

    @Test
    fun test_salaryInPiasters_negativeLargeValue_throwsException() {
        assertFailsWith<IllegalArgumentException> {
            UserProfileEntity(
                salaryInPiasters = -8500000L,
                payday = 15,
                currencyCode = "EGP",
                createdAt = 1706000000000L,
                updatedAt = 1706000000000L
            )
        }
    }

    @Test
    fun test_payday_negativeValue_throwsException() {
        assertFailsWith<IllegalArgumentException> {
            UserProfileEntity(
                salaryInPiasters = 8500000L,
                payday = -1,
                currencyCode = "EGP",
                createdAt = 1706000000000L,
                updatedAt = 1706000000000L
            )
        }
    }

    @Test
    fun test_payday_exceedsMax_throwsException() {
        assertFailsWith<IllegalArgumentException> {
            UserProfileEntity(
                salaryInPiasters = 8500000L,
                payday = 32,
                currencyCode = "EGP",
                createdAt = 1706000000000L,
                updatedAt = 1706000000000L
            )
        }
    }

    @Test
    fun test_payday_largeInvalidValue_throwsException() {
        assertFailsWith<IllegalArgumentException> {
            UserProfileEntity(
                salaryInPiasters = 8500000L,
                payday = 999,
                currencyCode = "EGP",
                createdAt = 1706000000000L,
                updatedAt = 1706000000000L
            )
        }
    }

    @Test
    fun test_payday_boundaryValue31_isValid() {
        // 31 is valid (max day of month)
        val entity = UserProfileEntity(
            salaryInPiasters = 8500000L,
            payday = 31,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )
        assertEquals(31, entity.payday)
    }

    @Test
    fun test_payday_boundaryValue0_isValid() {
        // 0 is valid (special: last day of month)
        val entity = UserProfileEntity(
            salaryInPiasters = 8500000L,
            payday = 0,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )
        assertEquals(0, entity.payday)
    }

    @Test
    fun test_salaryInPiasters_boundaryValueZero_isValid() {
        // 0 is valid (user has no salary set yet)
        val entity = UserProfileEntity(
            salaryInPiasters = 0L,
            payday = 15,
            currencyCode = "EGP",
            createdAt = 1706000000000L,
            updatedAt = 1706000000000L
        )
        assertEquals(0L, entity.salaryInPiasters)
    }
}
