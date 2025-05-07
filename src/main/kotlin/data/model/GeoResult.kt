package org.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GeoResult(
    val latitude: Double,
    val longitude: Double
)