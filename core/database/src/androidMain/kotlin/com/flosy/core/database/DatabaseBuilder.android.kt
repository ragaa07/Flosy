package com.flosy.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Android-specific context holder for database initialization.
 *
 * This must be initialized before any database operations.
 * Typically done in Application.onCreate() or via Koin.
 *
 * Thread-safe: Uses volatile + synchronized for safe concurrent access.
 */
object AndroidDatabaseContext {
    @Volatile
    private var applicationContext: Context? = null
    private val lock = Any()

    /**
     * Initialize with application context.
     * Call this from Application.onCreate() or Koin module.
     *
     * Thread-safe: Can be called from any thread, but typically called
     * once from the main thread during app initialization.
     *
     * @param context Application context (NOT activity context)
     */
    fun init(context: Context) {
        synchronized(lock) {
            applicationContext = context.applicationContext
        }
    }

    /**
     * Get the application context.
     *
     * Thread-safe: Can be called from any thread after initialization.
     *
     * @throws IllegalStateException if not initialized
     */
    fun getContext(): Context {
        return applicationContext
            ?: throw IllegalStateException(
                "AndroidDatabaseContext not initialized. " +
                "Call AndroidDatabaseContext.init(context) in Application.onCreate()"
            )
    }
}

/**
 * Android implementation of database builder.
 *
 * Uses the application context to determine the database file location.
 * The database is stored in the app's private directory.
 *
 * @return RoomDatabase.Builder configured for Android
 */
actual fun getDatabaseBuilder(): RoomDatabase.Builder<FlosynDatabase> {
    val context = AndroidDatabaseContext.getContext()
    val dbFile = context.getDatabasePath(FlosynDatabase.DATABASE_NAME)
    return Room.databaseBuilder<FlosynDatabase>(
        context = context,
        name = dbFile.absolutePath
    )
}
