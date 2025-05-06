package org.example.data.dataSource

interface CommandExecutor {
    fun execute(command: List<String>): String
}