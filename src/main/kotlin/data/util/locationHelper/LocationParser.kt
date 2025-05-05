package org.example.data.util.locationHelper

interface LocationParser {
    fun parse(body: String): Pair<Double, Double>
}