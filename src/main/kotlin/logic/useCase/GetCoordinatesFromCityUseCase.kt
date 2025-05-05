package org.example.logic.useCase

import org.example.logic.repository.GetCoordinatesFromCityRepository
import org.example.logic.model.Coordinates

class GetCoordinatesFromCityUseCase(
    private val repository: GetCoordinatesFromCityRepository
) {
    fun execute(city: String): Coordinates? {
        return repository.getCoordinatesForCity(city)
    }
}