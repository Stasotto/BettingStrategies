package com.example.data

import com.example.data.storage.DataStorage
import com.example.data.storage.RoomStorage
import com.example.domain.models.StrategyDomain
import com.example.domain.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl(
    private val roomStorage: RoomStorage,
    private val dataStorage: DataStorage
) : Repository {

    override suspend fun getAllFromDB(): List<StrategyDomain> {
        return withContext(Dispatchers.IO) {
            roomStorage.getAll().map { strategyEntity ->
                strategyEntity.toStrategyDomain()
            }
        }
    }

    override suspend fun saveToDb(strategy: StrategyDomain) {
        withContext(Dispatchers.IO) {
            roomStorage.save(strategy.toStrategyEntity())
        }
    }

    override suspend fun deleteFromDB(strategy: StrategyDomain) {
        withContext(Dispatchers.IO) {
            roomStorage.delete(strategy.toStrategyEntity())
        }
    }

    override suspend fun getAllData(): List<StrategyDomain> {
        return withContext(Dispatchers.IO) {
            dataStorage.getAll().map { strategyEntity ->
                strategyEntity.toStrategyDomain()
            }
        }
    }
}