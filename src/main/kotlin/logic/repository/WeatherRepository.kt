package org.example.logic.repository

import org.example.logic.model.WeatherData

interface WeatherRepository {
    suspend fun getWeatherByLocation(latitude: Double, longitude: Double, ): WeatherData?
}