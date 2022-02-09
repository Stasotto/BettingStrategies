package com.example.domain.usecase

import com.example.domain.models.StrategyDomain
import com.example.domain.repositories.Repository

class DeleteStrategyFromDBUseCase(private val repository: Repository) {

    suspend fun execute(strategy: StrategyDomain) {
        repository.deleteFromDB(strategy)
    }

}