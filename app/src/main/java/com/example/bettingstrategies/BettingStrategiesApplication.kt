package com.example.bettingstrategies

import android.app.Application
import com.example.bettingstrategies.presentation.di.domainModule
import com.example.bettingstrategies.presentation.di.presentationModule
import com.example.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BettingStrategiesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BettingStrategiesApplication)
            modules(
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}