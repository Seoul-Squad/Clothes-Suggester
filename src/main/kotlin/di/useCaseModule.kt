package org.example.di

import org.example.logic.useCase.ClothingSuggestionUseCase
import org.example.logic.useCase.GetCoordinatesFromCityNameUseCase
import org.example.logic.useCase.GetCurrentLocationUseCase
import org.example.logic.useCase.GetCurrentWeatherByLocationUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCurrentLocationUseCase(get()) }
    single { GetCoordinatesFromCityNameUseCase(get()) }
    single { GetCurrentWeatherByLocationUseCase(get()) }
    single { ClothingSuggestionUseCase() }
}
