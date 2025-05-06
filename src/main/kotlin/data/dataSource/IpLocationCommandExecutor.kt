package org.example.data.dataSource

class IpLocationCommandExecutor(
    private val cliCommand: List<String> = listOf("curl", "-s", "https://ipinfo.io/json")
) : CommandExecutor {
    override fun execute(command: List<String>): String {
        val process = ProcessBuilder(cliCommand)
            .redirectErrorStream(true)
            .start()
        return  process.inputStream.bufferedReader().use { it.readText() }.also {
            process.waitFor()
        }
    }
}