package org.example.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.logic.model.Coordinates
import org.example.logic.repository.CoordinatesFromCityRepository
import org.example.data.model.GeocodingResponse
import kotlinx.serialization.json.Json

class CoordinatesFromCityRepositoryImpl(
    private val client: HttpClient,
    private val json: Json
) : CoordinatesFromCityRepository {

    override suspend fun getCoordinatesForCity(city: String): Coordinates? {
        val url = "https://geocoding-api.open-meteo.com/v1/search?name=$city&count=1"

        return try {
            val responseText = client.get(url).body<String>()
            val response = json.decodeFromString<GeocodingResponse>(responseText)
            val firstResult = response.results?.firstOrNull()

            if (firstResult != null) {
                Coordinates(firstResult.latitude, firstResult.longitude)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}