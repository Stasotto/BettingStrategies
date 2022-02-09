package com.example.data.storage

import com.example.data.models.StrategyEntity

interface RoomStorage {

    suspend fun getAll(): List<StrategyEntity>

    suspend fun save(strategy: StrategyEntity)

    suspend fun delete(strategy: StrategyEntity)
}