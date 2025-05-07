package org.example.logic.repository

import org.example.logic.model.Coordinates

interface LocationRepository {
    suspend fun getCurrentLocation(): Coordinates
}