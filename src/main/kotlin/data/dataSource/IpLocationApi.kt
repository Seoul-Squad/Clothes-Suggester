package org.example.data.dataSource

interface IpLocationApi {
    suspend fun fetchLocationJson(): String
}