package com.example.data.storage

import android.content.Context
import com.example.data.models.StrategyEntity

class DataStorageImpl(private val context: Context) : DataStorage {

    override suspend fun getAll(): List<StrategyEntity> {
        return StrategyEntity.getStrategies(context)
    }
}