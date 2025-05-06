package data.model

import kotlinx.serialization.SerialName

data class CurrentWeather(
    @SerialName("temperature_2m") val temperature: Double,
    @SerialName("weather_code")val weatherCode: Int
)