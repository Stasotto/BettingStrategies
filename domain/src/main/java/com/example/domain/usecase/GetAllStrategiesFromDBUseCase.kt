package com.example.domain.usecase

import com.example.domain.repositories.Repository
import com.example.domain.models.StrategyDomain

class GetAllStrategiesFromDBUseCase(private val repository: Repository) {

    suspend fun execute(): List<StrategyDomain> {
        return repository.getAllFromDB()
    }
}