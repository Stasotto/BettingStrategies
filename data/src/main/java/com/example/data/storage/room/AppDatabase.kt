package com.example.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.StrategyEntity

@Database(
    entities = [
        StrategyEntity::class
    ],
    version = AppDatabase.VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1
    }

    abstract fun getStrategyDao(): StrategyDao
}