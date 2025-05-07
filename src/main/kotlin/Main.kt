package org.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.example.data.dataSource.IpInfoLocationParser
import org.example.data.dataSource.KtorIpLocationApi
import org.example.data.repository.LocationRepositoryImpl
import org.example.logic.useCase.GetCurrentLocationUseCase

fun main() = runBlocking(Dispatchers.IO) {
    val client = HttpClient(CIO)
    val api = KtorIpLocationApi(client)
    val parser = IpInfoLocationParser()
    val repository = LocationRepositoryImpl(api, parser)
    val useCase = GetCurrentLocationUseCase(repository)

    try {
        val cords = useCase()
        println("Current location: Latitude=${cords.latitude}, Longitude=${cords.longitude}")
    } catch (e: Exception) {
        println("Failed to fetch location: ${e.message}")
    } finally {
        client.close()
    }
}
