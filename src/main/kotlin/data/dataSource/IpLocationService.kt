package org.example.data.dataSource

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class IpLocationService(
    private val httpClient: HttpClient
) {
    suspend fun getLocationFromJson(url: String = "https://ipinfo.io/json"): String =
        httpClient.get(url).bodyAsText()
}