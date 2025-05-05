package org.example.data.util

import logic.model.WeatherCondition

object WeatherCodeMapper {
    fun map(code: Int): WeatherCondition = when (code) {
        in 0..1 -> WeatherCondition.CLEAR
        in 2..3 -> WeatherCondition.CLOUDY
        in 45..48 -> WeatherCondition.FOGGY
        in 51..67 -> WeatherCondition.DRIZZLE
        in 71..77 -> WeatherCondition.SNOW
        in 80..82 -> WeatherCondition.RAIN
        in 95..99 -> WeatherCondition.STORM
        else -> WeatherCondition.UNKNOWN
    }
}

