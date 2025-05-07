package org.example.logic.useCase

import logic.model.WeatherCondition
import org.example.logic.model.Weather
import org.example.logic.util.WeatherConstants

class ClothingSuggestionUseCase {
    operator fun invoke(weather: Weather):String {
        val temperature = weather.temperature
        val weatherState = weather.weatherState
        val isRaining = weather.isRaining

        val clotheSuggestion = getSuggestClothes(temperature)

        val weatherStateDescription = getWeatherState(weatherState)

        val rainNote = getRainNote(isRaining)

        return "${temperature}°C: $clotheSuggestion. $weatherStateDescription. $rainNote"
    }
    private fun getWeatherState(weatherState: WeatherCondition): String {
        val weatherStateDescription = when (weatherState) {
            WeatherCondition.CLEAR -> "Clear skies 🌞"
            WeatherCondition.CLOUDY -> "Cloudy ☁️"
            WeatherCondition.FOGGY -> "Foggy 🌫️"
            WeatherCondition.DRIZZLE -> "Drizzle 🌧️"
            WeatherCondition.SNOW -> "Snow ❄️"
            WeatherCondition.RAIN -> "Rain 🌧️"
            WeatherCondition.STORM -> "Storm ⛈️"
            WeatherCondition.UNKNOWN -> "Unknown weather"
        }
        return weatherStateDescription
    }
    private fun getSuggestClothes(temperature: Double): String {
        val clotheSuggestion = when {
            temperature < WeatherConstants.FREEZING_TEMP ->
                "❄️ Cold weather: Heavy coat, warm scarf, gloves"

            temperature < WeatherConstants.COLD_TEMP ->
                "🌤️ Cool weather: Light jacket, sweater, closed shoes"

            temperature < WeatherConstants.WARM_TEMP ->
                "☀️ Mild weather: Long-sleeve top, comfortable trousers"

            else ->
                "🔥 Hot weather: Short-sleeve shirt, shorts or light skirt"
        }
        return clotheSuggestion
    }
    private fun getRainNote(isRaining: Boolean): String = if (isRaining) "Don't forget your umbrella! ☂️" else  "No rain expected. 🌤️"

}