package org.example.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.data.util.locationHelper.CommandExecutor
import org.example.data.util.locationHelper.IpLocationCommandExecutor
import org.example.data.util.locationHelper.IpInfoLocationParser
import org.example.data.util.locationHelper.LocationParser
import org.example.logic.model.Coordinates
import org.example.logic.repository.LocationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LocationRepositoryImpl(
    private val executor: CommandExecutor = IpLocationCommandExecutor(),
    private val parser: LocationParser = IpInfoLocationParser(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val logger: Logger = LoggerFactory.getLogger(LocationRepositoryImpl::class.java)
) : LocationRepository {

    override suspend fun getCurrentLocation(): Result<Coordinates> =
        withContext(ioDispatcher) {
            runCatching {
                val response = executor.execute(emptyList())
                val (lat, lon) = parser.parse(response)
                Result.success(Coordinates(lat, lon))
            }.getOrElse {
                logger.error("Error fetching current location", it)
                Result.failure(it)
            }
        }
}