package org.example.logic.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.example.logic.model.Coordinates
import org.example.logic.repository.LocationRepository

class GetCurrentLocationUseCase(
    private val locationRepository: LocationRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Coordinates = coroutineScope {
        async(dispatcher) {
            locationRepository.getCurrentLocation()
        }.await()
    }
}