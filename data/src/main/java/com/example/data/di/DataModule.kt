package com.example.data.di

import androidx.room.Room
import com.example.data.storage.DataStorage
import com.example.data.storage.DataStorageImpl
import com.example.data.storage.RoomStorage
import com.example.data.storage.RoomStorageImpl
import com.example.data.storage.room.AppDatabase
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "strategy"
        ).build()
    }

    single {
        get<AppDatabase>().getStrategyDao()
    }

    single<RoomStorage> {
        RoomStorageImpl(strategyDao = get())
    }

    single<DataStorage> {
        DataStorageImpl(context = get())
    }
}