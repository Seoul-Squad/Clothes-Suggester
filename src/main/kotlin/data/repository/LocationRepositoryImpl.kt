package org.example.data.repository

import org.example.data.dataSource.CommandExecutor
import org.example.data.dataSource.IpLocationCommandExecutor
import org.example.data.dataSource.IpInfoLocationParser
import org.example.data.dataSource.LocationParser
import org.example.logic.model.Coordinates
import org.example.logic.repository.LocationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LocationRepositoryImpl(
    private val executor: CommandExecutor = IpLocationCommandExecutor(),
    private val parser: LocationParser = IpInfoLocationParser(),
    private val logger: Logger = LoggerFactory.getLogger(LocationRepositoryImpl::class.java)
) : LocationRepository {

    override suspend fun getCurrentLocation(): Coordinates =
            try {
                val response = executor.execute(emptyList())
                parser.parse(response)
            } catch (e: Exception) {
                logger.error("Error fetching current location", e)
                throw e
            }
}
