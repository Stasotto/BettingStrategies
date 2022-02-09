package com.example.domain.usecase

import com.example.domain.repositories.Repository
import com.example.domain.models.StrategyDomain

class SaveStrategyToDBUseCase(private val repository: Repository) {

    suspend fun execute(strategy: StrategyDomain) {
        repository.saveToDb(strategy)
    }
}