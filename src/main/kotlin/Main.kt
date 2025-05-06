package org.example

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.example.data.repository.LocationRepositoryImpl
import org.example.logic.useCase.GetCurrentLocationUseCase

fun main() = runBlocking(Dispatchers.IO) {
    val repository = LocationRepositoryImpl()
    val useCase = GetCurrentLocationUseCase(repository, dispatcher = Dispatchers.IO)
    try {
        val cords = useCase()
        println("Current location: Latitude=${cords.latitude}, Longitude=${cords.longitude}")
    } catch (e: Exception) {
        println("Failed to fetch location: ${e.message}")
    }
}
