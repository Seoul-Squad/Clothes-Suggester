package org.example.logic.useCase

import org.example.logic.repository.CoordinatesFromCityRepository
import org.example.logic.model.Coordinates

class GetCoordinatesFromCityUseCase(
    private val repository: CoordinatesFromCityRepository
) {
    fun invoke(city: String): Coordinates? {
        return repository.getCoordinatesForCity(city)
    }
}