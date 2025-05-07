package org.example.logic.useCase

import org.example.logic.repository.CoordinatesFromCityRepository
import org.example.logic.model.Coordinates

class CoordinatesFromCityUseCase(
    private val repository: CoordinatesFromCityRepository
) {
    suspend operator fun invoke(city: String): Coordinates? {
        return repository.getCoordinatesForCity(city)
    }
}