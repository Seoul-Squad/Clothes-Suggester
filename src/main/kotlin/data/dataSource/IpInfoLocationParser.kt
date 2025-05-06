package org.example.data.dataSource

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.example.logic.model.Coordinates

class IpInfoLocationParser : LocationParser {
    private val jsonParser = Json { ignoreUnknownKeys = true }

    companion object {
        private const val KEY_LOCATION = "loc"
    }

    override fun parse(body: String): Coordinates {
        val json = jsonParser
            .parseToJsonElement(body)
            .jsonObject

        val locString = json[KEY_LOCATION]
            ?.jsonPrimitive
            ?.content
            ?: throw IllegalStateException("Field '$KEY_LOCATION' not found in JSON response")

        val (latitude, longitude) = locString
            .split(",")
            .map(String::trim)
            .map(String::toDouble)

        return Coordinates(latitude, longitude)
    }
}
