package com.example.data.storage.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.models.StrategyEntity

@Dao
interface StrategyDao {

    @Query("SELECT * FROM strategy")
    fun getAll(): List<StrategyEntity>

    @Insert
    fun insert(strategy: StrategyEntity)

    @Delete
    fun delete(strategy: StrategyEntity)
}