package org.example.data.util

import logic.model.WeatherCondition

object WeatherCodeMapper {
    private val CLEAR_CODES = 0..1
    private val CLOUDY_CODES = 2..3
    private val FOGGY_CODES = 45..48
    private val DRIZZLE_CODES = 51..67
    private val SNOW_CODES = 71..77
    private val RAIN_CODES = 80..82
    private val STORM_CODES = 95..99
    fun Int.toWeatherCondition(): WeatherCondition = when (this) {
        in CLEAR_CODES -> WeatherCondition.CLEAR
        in CLOUDY_CODES -> WeatherCondition.CLOUDY
        in FOGGY_CODES -> WeatherCondition.FOGGY
        in DRIZZLE_CODES -> WeatherCondition.DRIZZLE
        in SNOW_CODES -> WeatherCondition.SNOW
        in RAIN_CODES -> WeatherCondition.RAIN
        in STORM_CODES -> WeatherCondition.STORM
        else -> WeatherCondition.UNKNOWN
    }
}

