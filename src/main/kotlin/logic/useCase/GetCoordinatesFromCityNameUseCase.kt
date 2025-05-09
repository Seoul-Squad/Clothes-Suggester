package org.example.logic.useCase

import org.example.logic.model.Coordinates
import org.example.logic.repository.LocationRepository

class GetCoordinatesFromCityNameUseCase(
    private val locationRepository: LocationRepository,
) {
    suspend operator fun invoke(city: String): Coordinates? =
        locationRepository.getCoordinatesFromCityName(city)
}
