package data.model

import kotlinx.serialization.SerialName

data class CurrentUnits(
    val interval: String,
    @SerialName("temperature_2m") val temperature: String,
    val time: String,
    @SerialName("weather_code")val weatherCode: String
)