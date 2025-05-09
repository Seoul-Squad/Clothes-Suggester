package org.example.logic.repository

import org.example.logic.model.Weather

interface WeatherRepository {
    suspend fun getWeatherByLocation(latitude: Double, longitude: Double, ): Weather
}