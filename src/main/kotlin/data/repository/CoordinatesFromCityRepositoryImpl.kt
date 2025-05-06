package org.example.data.repository

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.example.logic.model.Coordinates
import org.example.logic.repository.CoordinatesFromCityRepository

class CoordinatesFromCityRepositoryImpl : CoordinatesFromCityRepository {
    override fun getCoordinatesForCity(city: String): Coordinates? {
        val url = "https://geocoding-api.open-meteo.com/v1/search?name=${city}&count=1"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) return null

        val json = JSONObject(response.body?.string() ?: return null)
        val results = json.optJSONArray("results") ?: return null
        if (results.length() == 0) return null

        val locationObject = results.getJSONObject(0)
        val lat = locationObject.optDouble("latitude", Double.NaN)
        val lon = locationObject.optDouble("longitude", Double.NaN)

        return if (lat.isNaN() || lon.isNaN()) null else Coordinates(lat, lon)
    }

}