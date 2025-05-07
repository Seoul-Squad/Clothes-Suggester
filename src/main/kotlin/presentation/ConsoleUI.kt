package org.example.presentation

import org.example.logic.model.Coordinates
import org.example.logic.model.Weather
import org.example.logic.useCase.ClothingSuggestionUseCase
import org.example.logic.useCase.CoordinatesFromCityUseCase
import org.example.logic.useCase.GetCurrentLocationUseCase
import org.example.logic.useCase.GetCurrentWeatherByLocationUseCase
import org.example.presentation.util.Reader
import org.example.presentation.util.Viewer

class ConsoleUI(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val coordinatesFromCityUseCase: CoordinatesFromCityUseCase,
    private val weatherUseCase: GetCurrentWeatherByLocationUseCase,
    private val suggestionUseCase: ClothingSuggestionUseCase,
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
        val weather: Weather? = weatherUseCase(coordinates.latitude, coordinates.longitude)
        if (weather == null) {
            println("Failed to fetch weather.")
            return
        }
        val suggestion = suggestionUseCase(weather)
        println("--- Weather Info ---")
        println("Location: ${coordinates.latitude}, ${coordinates.longitude}")
        println("Temperature: ${weather.temperature}°C")
        println("Condition: ${weather.weatherState.description}")
        println(suggestion)
        println("--------------------")
    }
}