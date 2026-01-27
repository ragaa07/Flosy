package com.flosy.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing the user's profile configuration.
 *
 * This is the core entity that stores the user's financial setup:
 * - Monthly salary (in piasters for precision)
 * - Payday (day of month when salary arrives)
 * - Currency preference
 *
 * Note: This app assumes a single user profile at a time.
 *
 * ## Validation Rules
 * - `salaryInPiasters` must be >= 0 (non-negative)
 * - `payday` must be 0-31 (0 = last day of month, 1-31 = specific day)
 *
 * ## First-Launch Behavior
 * When `getProfile()` returns null, the app should redirect to onboarding.
 *
 * @property id Auto-generated primary key
 * @property salaryInPiasters Monthly salary stored in piasters (1 EGP = 100 piasters).
 *                            Example: 85,000 EGP = 8,500,000 piasters. Must be >= 0.
 * @property payday Day of month when user gets paid (0-31).
 *                  Special value 0 means "last day of month". Values 1-31 specify the day.
 * @property currencyCode Currency code (default: "EGP")
 * @property createdAt Timestamp when profile was created (epoch milliseconds)
 * @property updatedAt Timestamp when profile was last updated (epoch milliseconds)
 * @throws IllegalArgumentException if salaryInPiasters < 0 or payday not in 0..31
 */
@Entity(tableName = "UserProfile")
data class UserProfileEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "salaryInPiasters")
    val salaryInPiasters: Long,

    @ColumnInfo(name = "payday")
    val payday: Int,

    @ColumnInfo(name = "currencyCode")
    val currencyCode: String = "EGP",

    @ColumnInfo(name = "createdAt")
    val createdAt: Long,

    @ColumnInfo(name = "updatedAt")
    val updatedAt: Long
) {
    init {
        require(salaryInPiasters >= 0) {
            "salaryInPiasters must be non-negative, got: $salaryInPiasters"
        }
        require(payday in 0..31) {
            "payday must be 0-31 (0 = last day of month), got: $payday"
        }
    }
}
