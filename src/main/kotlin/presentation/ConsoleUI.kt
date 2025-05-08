package org.example.presentation

import logic.model.WeatherCondition
import org.example.logic.model.Coordinates
import org.example.logic.model.Weather
import org.example.logic.useCase.CoordinatesFromCityUseCase
import org.example.logic.useCase.GetCurrentLocationUseCase
import org.example.logic.useCase.GetCurrentWeatherByLocationUseCase
import org.example.logic.util.WeatherConstants
import org.example.presentation.util.Reader
import org.example.presentation.util.Viewer

class ConsoleUI(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val coordinatesFromCityUseCase: CoordinatesFromCityUseCase,
    private val weatherUseCase: GetCurrentWeatherByLocationUseCase,
    private val viewer: Viewer,
    private val reader: Reader
) {
    suspend fun run() {
        while (true) {
            viewer.display("Choose an option:")
            viewer.display("1. Show weather for current location")
            viewer.display("2. Show weather for a city")
            viewer.display("3. Exit")

            when (reader.readString().trim()) {
                "1" -> showCurrentLocationWeather()
                "2" -> showCityWeather()
                "3" -> {
                    viewer.display("Goodbye!")
                    return
                }
                else -> viewer.display("Invalid option, please try again.")
            }
        }
    }
    private suspend fun showCurrentLocationWeather() {
        try {
            val coordinates = getCurrentLocationUseCase()
            displayWeather(coordinates)
        } catch (e: Exception) {
            viewer.display("Failed to fetch current location: ${e.message}")
        }
    }

    private suspend fun showCityWeather() {
        print("Enter city name: ")
        val city = reader.readString().trim()
        val coordinates: Coordinates? = coordinatesFromCityUseCase(city)
        if (coordinates == null) {
            println("Could not find coordinates for '$city'")
            return
        }
        displayWeather(coordinates)
    }

    private suspend fun displayWeather(coordinates: Coordinates) {
        val weather = weatherUseCase(coordinates.latitude,coordinates.longitude)
        val suggestion = getSuggestion(weather)
        println("--- Weather Info ---")
        println("Location: ${coordinates.latitude}, ${coordinates.longitude}")
        println("Temperature: ${weather.temperature}°C")
        println("weatherState: ${weather.weatherState.description}")
        println(suggestion)
        println("--------------------")
    }
    private fun getSuggestion(weather: Weather): String {
        val temperature = weather.temperature
        val isRaining = weather.isRaining
        val weatherState = weather.weatherState
        val weatherStateDescription = getWeatherState(weatherState)
        val clotheSuggestion = getSuggestClothes(temperature)
        val rainNote = getRainNote(isRaining)
        return "$weatherStateDescription. $clotheSuggestion. $rainNote"
    }
    private fun getWeatherState(weatherState: WeatherCondition): String {
        return when (weatherState) {
            WeatherCondition.CLEAR -> "Clear skies 🌞"
            WeatherCondition.CLOUDY -> "Cloudy ☁️"
            WeatherCondition.FOGGY -> "Foggy 🌫️"
            WeatherCondition.DRIZZLE -> "Drizzle 🌧️"
            WeatherCondition.SNOW -> "Snow ❄️"
            WeatherCondition.RAIN -> "Rain 🌧️"
            WeatherCondition.STORM -> "Storm ⛈️"
        }
    }

    private fun getSuggestClothes(temperature: Double): String {
        return when {
            temperature < WeatherConstants.FREEZING_TEMP ->
                "❄️ Cold weather: Heavy coat, warm scarf, gloves"
            temperature < WeatherConstants.COLD_TEMP ->
                "🌤️ Cool weather: Light jacket, sweater, closed shoes"
            temperature < WeatherConstants.WARM_TEMP ->
                "☀️ Mild weather: Long-sleeve top, comfortable trousers"
            else ->
                "🔥 Hot weather: Short-sleeve shirt, shorts or light skirt"
        }
    }

    private fun getRainNote(isRaining: Boolean): String =
        if (isRaining) "Don't forget your umbrella! ☂️" else "No rain expected. 🌤️"
}