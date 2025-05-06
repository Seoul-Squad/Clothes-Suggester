package org.example.data.repository

import data.model.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.example.data.util.WeatherCodeMapper.toWeatherCondition
import org.example.logic.model.Weather
import org.example.logic.repository.WeatherRepository

class WeatherRepositoryImpl(private val client: HttpClient) : WeatherRepository {

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getWeatherByLocation(latitude: Double, longitude: Double): Weather? {
        val url = getBaseUrl(latitude, longitude)
        val response = client.get(url)
        val weatherResponse = json.decodeFromString<WeatherResponse>(response.bodyAsText())
        val temperature = weatherResponse.currentWeather.temperature
        val weatherCode = weatherResponse.currentWeather.weatherCode
        val weatherState = weatherCode.toWeatherCondition()
        val isRaining = weatherCode in rainWeatherCodes
        return Weather(temperature, weatherState, isRaining)
    }

    private fun getBaseUrl(latitude: Double, longitude: Double): String {
        return "https://api.open-meteo.com/v1/forecast" +
                "?latitude=$latitude" +
                "&longitude=$longitude" +
                "&current=rain,temperature_2m,weather_code"
    }

    companion object {
        private val rainWeatherCodes: List<Int> = listOf(51, 53, 55, 61, 63, 65, 80, 81, 82)
    }
}