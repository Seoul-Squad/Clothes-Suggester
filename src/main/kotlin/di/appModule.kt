package org.example.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import kotlinx.serialization.json.Json
import org.example.data.dataSource.IpInfoLocationParser
import org.example.data.dataSource.IpLocationApi
import org.example.data.dataSource.KtorIpLocationApi
import org.example.data.dataSource.LocationParser
import org.example.data.repository.CoordinatesFromCityRepositoryImpl
import org.example.data.repository.LocationRepositoryImpl
import org.example.data.repository.WeatherRepositoryImpl
import org.example.logic.repository.CoordinatesFromCityRepository
import org.example.logic.repository.LocationRepository
import org.example.logic.repository.WeatherRepository
import org.example.logic.useCase.ClothingSuggestionUseCase
import org.example.logic.useCase.CoordinatesFromCityUseCase
import org.example.logic.useCase.GetCurrentLocationUseCase
import org.example.logic.useCase.GetCurrentWeatherByLocationUseCase
import org.example.presentation.ConsoleUI
import org.example.presentation.util.ConsoleReader
import org.example.presentation.util.ConsoleViewer
import org.koin.dsl.module
import org.example.presentation.util.Reader
import org.example.presentation.util.Viewer
import org.koin.core.module.dsl.singleOf

val appModule = module {
    single { HttpClient(CIO) }
    single { Json { ignoreUnknownKeys = true } }

    single<IpLocationApi> { KtorIpLocationApi(get()) }
    single { IpInfoLocationParser() }
    single<LocationParser> { IpInfoLocationParser() }

    single<LocationRepository> { LocationRepositoryImpl(get(), get()) }
    single<CoordinatesFromCityRepository> { CoordinatesFromCityRepositoryImpl(get(), get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }


    single { GetCurrentLocationUseCase(get()) }
    single { CoordinatesFromCityUseCase(get()) }
    single { GetCurrentWeatherByLocationUseCase(get()) }
    single { ClothingSuggestionUseCase() }

    single<Viewer> { ConsoleViewer() }
    single<Reader> { ConsoleReader() }
    singleOf(::ConsoleUI)
}