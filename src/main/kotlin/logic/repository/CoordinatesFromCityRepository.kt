package org.example.logic.repository
import org.example.logic.model.Coordinates

interface CoordinatesFromCityRepository {
    fun getCoordinatesForCity(city: String): Coordinates?
}
