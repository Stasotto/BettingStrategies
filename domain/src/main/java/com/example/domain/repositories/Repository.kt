package com.example.domain.repositories

import com.example.domain.models.StrategyDomain

interface Repository {

    suspend fun getAllFromDB(): List<StrategyDomain>

    suspend fun saveToDb(strategy: StrategyDomain)

    suspend fun deleteFromDB(strategy: StrategyDomain)

    suspend fun getAllData(): List<StrategyDomain>
}