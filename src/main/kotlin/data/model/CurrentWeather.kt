package data.model

import kotlinx.serialization.SerialName

data class CurrentWeather(
    val current: Current,
    @SerialName("current_units") val currentUnits: CurrentUnits,
    val elevation: Int,
    @SerialName("enerationtime_ms")val generationtime: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerialName("timezone_abbreviation")val timezoneAbbreviation: String,
    @SerialName("utc_offset_seconds")val utcOffsetSeconds: Int
)