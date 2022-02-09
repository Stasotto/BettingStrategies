package com.example.data.storage

import com.example.data.models.StrategyEntity
import com.example.data.storage.room.StrategyDao

class RoomStorageImpl(private val strategyDao: StrategyDao) : RoomStorage {

    override suspend fun getAll(): List<StrategyEntity> {
        return strategyDao.getAll()
    }

    override suspend fun save(strategy: StrategyEntity) {
        strategyDao.insert(strategy)
    }

    override suspend fun delete(strategy: StrategyEntity) {
        strategyDao.delete(strategy)
    }
}