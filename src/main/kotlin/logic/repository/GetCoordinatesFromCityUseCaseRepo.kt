package org.example.logic.repository
import org.example.logic.model.Coordinates

interface GetCoordinatesFromCityRepository {
    fun getCoordinatesForCity(city: String): Coordinates?
}
