package org.example.logic.model

import kotlinx.datetime.LocalDate
import logic.model.WeatherCondition

data class WeatherData(
    val temperature: Double,
    val weatherState: WeatherCondition,
    val isRaining: Boolean,
)
