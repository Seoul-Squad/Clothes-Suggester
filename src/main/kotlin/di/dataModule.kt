package org.example.di

import org.example.data.dataSource.IpLocationJsonParser
import org.example.data.dataSource.IpLocationService
import org.example.data.repository.LocationRepositoryImpl
import org.example.data.repository.WeatherRepositoryImpl
import org.example.logic.repository.LocationRepository
import org.example.logic.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single { IpLocationJsonParser() }
    single { IpLocationService(get()) }

    single<LocationRepository> { LocationRepositoryImpl(get(), get(), get(), get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
}
