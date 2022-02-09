package com.example.domain.usecase

import com.example.domain.models.StrategyDomain
import com.example.domain.repositories.Repository

class GetAllDataUseCase(private val repository: Repository) {

    suspend fun execute(): List<StrategyDomain> {
        return repository.getAllData()
    }
}