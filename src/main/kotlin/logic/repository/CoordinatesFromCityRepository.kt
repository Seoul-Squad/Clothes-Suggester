package org.example.logic.repository
import org.example.logic.model.Coordinates

interface CoordinatesFromCityRepository {
    suspend fun getCoordinatesForCity(city: String): Coordinates?
}
