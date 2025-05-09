package org.example.di

import org.example.presentation.ConsoleUI
import org.example.presentation.util.ConsoleReader
import org.example.presentation.util.ConsoleViewer
import org.example.presentation.util.Reader
import org.example.presentation.util.Viewer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val presentationModule = module {
    single<Viewer> { ConsoleViewer() }
    single<Reader> { ConsoleReader() }
    singleOf(::ConsoleUI)
}
