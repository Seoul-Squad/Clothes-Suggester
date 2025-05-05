package org.example.data.util.locationHelper

interface CommandExecutor {
    fun execute(command: List<String>): String
}