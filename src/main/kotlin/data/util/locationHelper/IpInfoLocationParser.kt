package org.example.data.util.locationHelper

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class IpInfoLocationParser : LocationParser {
    private val jsonParser = Json { ignoreUnknownKeys = true }

    companion object {
        private const val KEY_LOCATION = "loc"
    }

    override fun parse(body: String): Pair<Double, Double> {
        val json = jsonParser
            .parseToJsonElement(body)
            .jsonObject

        val locString = json[KEY_LOCATION]
            ?.jsonPrimitive
            ?.content
            ?: throw IllegalStateException("Field '$KEY_LOCATION' not found in JSON response")

        val (lat, lon) = locString.split(",")
            .map(String::trim)
            .map(String::toDouble)

        return lat to lon
    }
}
