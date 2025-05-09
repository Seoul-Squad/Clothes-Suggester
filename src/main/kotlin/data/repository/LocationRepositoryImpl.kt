package org.example.data.repository

import kotlinx.serialization.json.Json
import org.example.data.dataSource.IpLocationJsonParser
import org.example.data.dataSource.IpLocationService
import org.example.data.model.GeocodingResponse
import org.example.logic.model.Coordinates
import org.example.logic.repository.LocationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*

class LocationRepositoryImpl(
    private val ipLocationJsonParser: IpLocationJsonParser,
    private val ipLocationService: IpLocationService,
    private val client: HttpClient,
    private val json: Json,
    private val logger: Logger = LoggerFactory.getLogger(LocationRepositoryImpl::class.java)
) : LocationRepository {

    override suspend fun getCurrentLocation(): Coordinates =
        try {
            val jsonBody = ipLocationService.getLocationFromJson()
            ipLocationJsonParser.parseCoordinates(jsonBody)
        } catch (e: Exception) {
            logger.error("Error fetching current location", e)
            throw e
        }

    override suspend fun getCoordinatesFromCityName(city: String): Coordinates? {
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
