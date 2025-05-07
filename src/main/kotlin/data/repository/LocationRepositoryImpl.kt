package org.example.data.repository

import org.example.data.dataSource.IpLocationApi
import org.example.data.dataSource.LocationParser
import org.example.logic.model.Coordinates
import org.example.logic.repository.LocationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LocationRepositoryImpl(
    private val api: IpLocationApi,
    private val parser: LocationParser,
    private val logger: Logger = LoggerFactory.getLogger(LocationRepositoryImpl::class.java)
) : LocationRepository {

    override suspend fun getCurrentLocation(): Coordinates =
        try {
            val jsonBody = api.fetchLocationJson()
            parser.parse(jsonBody)
        } catch (e: Exception) {
            logger.error("Error fetching current location", e)
            throw e
        }
}
