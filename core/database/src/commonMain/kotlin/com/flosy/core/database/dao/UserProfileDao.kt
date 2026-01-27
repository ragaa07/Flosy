package com.flosy.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.flosy.core.database.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for UserProfile operations.
 *
 * Provides CRUD operations for the user's profile configuration.
 * Uses Flow for reactive queries that automatically emit updates.
 */
@Dao
interface UserProfileDao {

    /**
     * Inserts or replaces a user profile.
     *
     * Uses REPLACE strategy - if a profile with the same ID exists,
     * it will be replaced with the new data.
     *
     * @param profile The profile entity to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: UserProfileEntity)

    /**
     * Updates an existing user profile.
     *
     * @param profile The profile entity with updated values
     */
    @Update
    suspend fun update(profile: UserProfileEntity)

    /**
     * Observes the user profile as a Flow.
     *
     * Returns a Flow that emits the current profile and any subsequent updates.
     * Emits null if no profile exists.
     *
     * Note: This app assumes a single user profile, hence LIMIT 1.
     *
     * @return Flow emitting the user profile or null
     */
    @Query("SELECT * FROM UserProfile LIMIT 1")
    fun getProfile(): Flow<UserProfileEntity?>

    /**
     * Gets the user profile once (non-reactive).
     *
     * Use this for one-time reads where you don't need to observe changes.
     *
     * @return The user profile or null if not set up
     */
    @Query("SELECT * FROM UserProfile LIMIT 1")
    suspend fun getProfileOnce(): UserProfileEntity?

    /**
     * Deletes all user profiles.
     *
     * Used for resetting the app or during testing.
     */
    @Query("DELETE FROM UserProfile")
    suspend fun deleteAll()
}
