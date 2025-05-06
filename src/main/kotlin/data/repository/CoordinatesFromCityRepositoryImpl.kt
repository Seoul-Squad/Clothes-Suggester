package org.example.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import org.example.logic.model.Coordinates
import org.example.logic.repository.CoordinatesFromCityRepository

class CoordinatesFromCityRepositoryImpl(
    private val client: HttpClient
) : CoordinatesFromCityRepository {

    override fun getCoordinatesForCity(city: String): Coordinates? = runBlocking {
        val url = "https://geocoding-api.open-meteo.com/v1/search?name=$city&count=1"

        val responseText = try {
            client.get(url).body<String>()
        } catch (e: Exception) {
            return@runBlocking null
        }

        val json = JSONObject(responseText)
        val results = json.optJSONArray("results") ?: return@runBlocking null
        if (results.length() == 0) return@runBlocking null

        val locationObject = results.getJSONObject(0)
        val lat = locationObject.optDouble("latitude", Double.NaN)
        val lon = locationObject.optDouble("longitude", Double.NaN)

        return@runBlocking if (lat.isNaN() || lon.isNaN()) null else Coordinates(lat, lon)
    }
}