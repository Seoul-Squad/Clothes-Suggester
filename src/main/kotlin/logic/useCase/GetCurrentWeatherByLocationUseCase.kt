package org.example.logic.useCase

import org.example.logic.model.Weather
import org.example.logic.repository.WeatherRepository


class GetCurrentWeatherByLocationUseCase(
    private val weatherRepository: WeatherRepository,
) {
    suspend operator fun invoke(latitude: Double,longitude: Double): Weather {
        return weatherRepository.getWeatherByLocation(latitude,longitude)
    }
}