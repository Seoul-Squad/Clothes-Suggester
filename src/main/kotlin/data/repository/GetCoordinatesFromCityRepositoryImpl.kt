package org.example.data.repository

import org.example.logic.repository.GetCoordinatesFromCityRepository
import org.example.logic.model.Coordinates

class GetCoordinatesFromCityRepositoryImpl : GetCoordinatesFromCityRepository {
    override fun getCoordinatesForCity(city: String): Coordinates? {
        return when (city.lowercase()) {
            //fake data
            "cairo" -> Coordinates(30.0444, 31.2357)
            "london" -> Coordinates(51.5074, -0.1278)
            else -> null
        }
    }
}