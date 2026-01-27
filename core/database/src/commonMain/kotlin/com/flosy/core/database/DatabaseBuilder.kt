package com.flosy.core.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

/**
 * Creates the FlosynDatabase instance with proper configuration.
 *
 * Uses BundledSQLiteDriver for consistent SQLite behavior across platforms
 * and sets up coroutine context for database operations.
 *
 * ## Migration Strategy
 *
 * **Development Phase (Current):**
 * - Uses `fallbackToDestructiveMigration()` to allow schema changes without manual migrations
 * - Data will be lost on schema version changes - acceptable for development
 *
 * **Production Phase (Future):**
 * - Replace with explicit `addMigrations(MIGRATION_1_2, MIGRATION_2_3, ...)`
 * - Each migration must be tested thoroughly before release
 * - Consider using Room's auto-migration for simple schema changes
 *
 * @param builder Platform-specific RoomDatabase.Builder
 * @return Configured FlosynDatabase instance
 */
fun createDatabase(builder: RoomDatabase.Builder<FlosynDatabase>): FlosynDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .fallbackToDestructiveMigration(dropAllTables = true) // TODO: Replace with explicit migrations before production
        .build()
}

/**
 * Gets the platform-specific database builder.
 *
 * Each platform implements this differently:
 * - Android: Uses application context to get database path
 * - iOS: Uses NSHomeDirectory for document storage
 *
 * @return Platform-specific RoomDatabase.Builder
 */
expect fun getDatabaseBuilder(): RoomDatabase.Builder<FlosynDatabase>
