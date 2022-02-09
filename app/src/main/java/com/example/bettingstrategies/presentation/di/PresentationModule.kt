package com.example.bettingstrategies.presentation.di

import com.example.bettingstrategies.presentation.viewmodels.StrategiesFragViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        StrategiesFragViewModel(
            getAllStrategiesFromDBUseCase = get(),
            saveStrategyToDBUseCase = get(),
            deleteStrategyFromDBUseCase = get(),
            getAllDataUseCase = get()
        )
    }
}