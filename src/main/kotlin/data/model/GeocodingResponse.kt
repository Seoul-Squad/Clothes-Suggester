package org.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GeocodingResponse(
    val results: List<GeoResult>?
)