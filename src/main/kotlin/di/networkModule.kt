package org.example.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single { HttpClient(CIO) }
    single { Json { ignoreUnknownKeys = true } }
}
