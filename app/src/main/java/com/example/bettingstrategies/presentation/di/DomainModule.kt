package com.example.bettingstrategies.presentation.di

import com.example.data.RepositoryImpl
import com.example.domain.repositories.Repository
import com.example.domain.usecase.DeleteStrategyFromDBUseCase
import com.example.domain.usecase.GetAllDataUseCase
import com.example.domain.usecase.GetAllStrategiesFromDBUseCase
import com.example.domain.usecase.SaveStrategyToDBUseCase
import org.koin.dsl.module

val domainModule = module {

    single<Repository> {
        RepositoryImpl(roomStorage = get(), dataStorage = get())
    }

    single {
        GetAllStrategiesFromDBUseCase(repository = get())
    }

    single {
        SaveStrategyToDBUseCase(repository = get())
    }

    single {
        DeleteStrategyFromDBUseCase(repository = get())
    }

    single {
        GetAllDataUseCase(repository = get())
    }

}