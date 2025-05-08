package org.example.data.util.mapper

import data.model.WeatherResponse
import org.example.logic.model.Weather

private val rainWeatherCodes: List<Int> = listOf(51, 53, 55, 61, 63, 65, 80, 81, 82)
fun WeatherResponse.toWeather(weatherResponse: WeatherResponse): Weather {
    val currentWeather = weatherResponse.currentWeather
    val temperature = currentWeather.temperature
    val weatherCode = currentWeather.weatherCode
    val weatherState = weatherCode.toWeatherCondition()
    val isRaining = weatherCode in rainWeatherCodes
    return Weather(temperature, weatherState, isRaining)
}

