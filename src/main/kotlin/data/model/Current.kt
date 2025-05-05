package data.model

import kotlinx.serialization.SerialName

data class Current(
    val interval: Int,
    @SerialName("temperature_2m") val temperature: Double,
    val time: String,
    @SerialName("weather_code")val weatherCode: Int
)