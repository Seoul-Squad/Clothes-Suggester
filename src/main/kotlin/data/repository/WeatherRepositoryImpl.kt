package org.example.data.repository

import data.model.CurrentWeather
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.example.data.util.WeatherCodeMapper
import org.example.logic.model.WeatherData
import org.example.logic.repository.WeatherRepository

class WeatherRepositoryImpl : WeatherRepository {
    private val client = HttpClient(CIO)

    override suspend fun getWeatherByLocation(latitude: Double, longitude: Double): WeatherData? {
        val url = getBaseUrl(latitude, longitude)
        val response = client.get(url)
        val weatherResponse = Json.decodeFromString<CurrentWeather>(response.bodyAsText())
        val temperature = weatherResponse.current.temperature
        val weatherCode = weatherResponse.current.weatherCode
        val weatherState = WeatherCodeMapper.map(weatherCode)
        val isRaining = weatherCode in rainWeatherCodes
        return WeatherData(temperature, weatherState, isRaining)
    }

    private fun getBaseUrl(latitude: Double, longitude: Double): String {
        return "https://api.open-meteo.com/v1/forecast" +
                "?latitude=$latitude" +
                "&longitude=$longitude" +
                "&current=rain,temperature_2m,weather_code"
    }
    companion object {
        private  val rainWeatherCodes: List<Int> = listOf(51, 53, 55, 61, 63, 65, 80, 81, 82)
    }
}
