package com.flosy.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

/**
 * iOS implementation of database builder.
 *
 * Uses the app's Documents directory for database storage.
 * This ensures the database persists across app launches.
 *
 * @return RoomDatabase.Builder configured for iOS
 */
actual fun getDatabaseBuilder(): androidx.room.RoomDatabase.Builder<com.flosy.core.database.FlosynDatabase> {
    val dbFilePath = documentDirectory() + "/${FlosynDatabase.DATABASE_NAME}"
    return Room.databaseBuilder<FlosynDatabase>(
        name = dbFilePath
    )
}

/**
 * Gets the iOS Documents directory path.
 *
 * The Documents directory is the standard location for user-generated
 * data that should persist and be backed up.
 *
 * @return Absolute path to the Documents directory
 */
private fun documentDirectory(): String {
    val fileManager = NSFileManager.defaultManager
    val urls = fileManager.URLsForDirectory(
        directory = NSDocumentDirectory,
        inDomains = NSUserDomainMask
    )
    @Suppress("UNCHECKED_CAST")
    val documentUrl = urls.firstOrNull() as? platform.Foundation.NSURL
        ?: throw IllegalStateException("Could not find Documents directory")
    return documentUrl.path ?: throw IllegalStateException("Documents directory path is null")
}