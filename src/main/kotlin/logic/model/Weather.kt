package org.example.logic.model

import logic.model.WeatherCondition

data class Weather(
    val temperature: Double,
    val weatherState: WeatherCondition,
    val isRaining: Boolean,
)
