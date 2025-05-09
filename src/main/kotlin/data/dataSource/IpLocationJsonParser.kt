package org.example.data.dataSource

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.example.logic.model.Coordinates

class IpLocationJsonParser {
    private val jsonParser = Json { ignoreUnknownKeys = true }

    companion object {
        private const val KEY_LOCATION = "loc"
    }

    fun parseCoordinates(body: String): Coordinates {
        val json = jsonParser
            .parseToJsonElement(body)
            .jsonObject

        val locationString = json[KEY_LOCATION]
            ?.jsonPrimitive
            ?.content
            ?: throw IllegalStateException("Field 'loc' not found in JSON response")

        val (latitude, longitude) = locationString
            .split(",")
            .map(String::trim)
            .map(String::toDouble)

        return Coordinates(latitude, longitude)
    }
}