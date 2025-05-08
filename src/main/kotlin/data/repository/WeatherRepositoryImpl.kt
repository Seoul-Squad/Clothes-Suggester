package org.example.data.repository

import data.model.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import org.example.data.util.mapper.toWeather
import org.example.logic.model.Weather
import org.example.logic.repository.WeatherRepository
import org.example.data.util.Constants.BASE_URL

class WeatherRepositoryImpl(private val client: HttpClient , private val json : Json) : WeatherRepository {

    override suspend fun getWeatherByLocation(latitude: Double, longitude: Double): Weather {
        val url = getBaseUrl(latitude, longitude)
        val response = client.get(url)
        val weatherResponse = json.decodeFromString<WeatherResponse>(response.bodyAsText())
        return weatherResponse.toWeather(weatherResponse)
    }

    private fun getBaseUrl(latitude: Double, longitude: Double): String {
        return "$BASE_URL?latitude=$latitude&longitude=$longitude&current=rain,temperature_2m,weather_code"
    }
}