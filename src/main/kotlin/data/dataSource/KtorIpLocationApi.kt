package org.example.data.dataSource

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class KtorIpLocationApi(
    private val httpClient: HttpClient,
    private val url: String = "https://ipinfo.io/json"
) : IpLocationApi {
    override suspend fun fetchLocationJson(): String =
        httpClient.get(url).bodyAsText()
}