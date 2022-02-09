package com.example.data.storage

import com.example.data.models.StrategyEntity

interface DataStorage {

    suspend fun getAll(): List<StrategyEntity>
}