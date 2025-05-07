package org.example

import kotlinx.coroutines.runBlocking
import org.example.di.appModule
import org.example.presentation.ConsoleUI
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    startKoin {
        modules(appModule)
    }
    val consoleUI: ConsoleUI = getKoin().get()
    runBlocking {
        consoleUI.run()
    }
}