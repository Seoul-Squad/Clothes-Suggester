package org.example.data.dataSource

import org.example.logic.model.Coordinates

interface LocationParser {
    fun parse(body: String): Coordinates
}