package org.example.logic.useCase

import org.example.logic.model.Coordinates
import org.example.logic.repository.LocationRepository

class GetCurrentLocationUseCase(
    private val locationRepository: LocationRepository,
) {
    suspend operator fun invoke(): Coordinates =
        locationRepository.getCurrentLocation()
}