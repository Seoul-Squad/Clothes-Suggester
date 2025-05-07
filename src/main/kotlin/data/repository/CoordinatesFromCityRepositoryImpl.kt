package org.example.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.Serializable
import org.example.logic.model.Coordinates
import org.example.logic.repository.CoordinatesFromCityRepository

@Serializable
data class GeocodingResponse(
    val results: List<GeoResult>?
)

@Serializable
data class GeoResult(
    val latitude: Double,
    val longitude: Double
)

class CoordinatesFromCityRepositoryImpl(
    private val client: HttpClient
) : CoordinatesFromCityRepository {

    override suspend fun getCoordinatesForCity(city: String): Coordinates? {
        val baseUrl = "https://geocoding-api.open-meteo.com/v1/search"
        val url = "$baseUrl?name=$city&count=1"

        return try {
            val response: GeocodingResponse = client.get(url).body()
            val result = response.results?.firstOrNull()
            result?.let { Coordinates(it.latitude, it.longitude) }
        } catch (e: Exception) {
            null
        }
    }
}