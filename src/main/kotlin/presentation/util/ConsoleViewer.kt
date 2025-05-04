package org.example.presentation.util

class ConsoleViewer : Viewer {
    override fun display(message: String?) {
        println(message)
    }
}