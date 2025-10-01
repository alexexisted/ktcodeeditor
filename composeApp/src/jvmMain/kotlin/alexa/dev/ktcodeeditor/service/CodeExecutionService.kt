package alexa.dev.ktcodeeditor.service

import alexa.dev.ktcodeeditor.data.ExecutionStateRepository
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
/**
 *  @property executionStateRepository repository that contains state object of code execution
 */
class CodeExecutionService(
    val executionStateRepository: ExecutionStateRepository
) {
    private val serviceScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun executeScript(script: String) {
        serviceScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    runKotlinScript(
                        script = script,
                        onOutput = { line ->
                            executionStateRepository.update {
                                it.copy(outputText = it.outputText + line + "\n")
                            } //update output with executed part
                        },
                        onExitCode = { exitCode ->
                            executionStateRepository.update {
                                it.copy(
                                    isRunning = false,
                                    outputText = it.outputText + "\nExit Code: $exitCode",
                                    exitCode = "$exitCode"
                                ) //add exit code to result of the script
                            }
                        }
                    )
                }
            } catch (e: Exception) { //if error then show exception
                executionStateRepository.update { it.copy(isRunning = false, outputText = e.message ?: "Unknown error") }
            } finally { //anyway finish the execution
                executionStateRepository.update { it.copy(isRunning = false) }
            }
        }
    }

    private suspend fun runKotlinScript(script: String, onOutput: (String) -> Unit, onExitCode: (Int) -> Unit) {
        withContext(Dispatchers.IO) { //run coroutine
            val kotlincPath = getKotlinCompilerPath() //getting the path

            //create script file to write there entered script
            val scriptFile = File.createTempFile("script", ".kts").apply {
                writeText(script)
            }

            //start process
            val process = ProcessBuilder(kotlincPath, "-script", scriptFile.absolutePath)
                .redirectErrorStream(true) //merge stderr and stdout to get errors from the same stdout
                .start()

            //read chunks of data from buffer
            val reader = BufferedReader(InputStreamReader(process.inputStream))

            try {
                while (true) {
                    val line = reader.readLine() ?: break //read a line
                    withContext(Dispatchers.Main) { //coroutine to update ui with line of text
                        onOutput(line)
                    }
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    onOutput("Error: ${e.message}") //throws exception to ui
                }
            } finally {
                process.waitFor()
                val exitCode = process.exitValue() //catching exit code
                withContext(Dispatchers.Main) { onExitCode(exitCode) } //update ui with exit code
                reader.close()
            }
        }
    }

    private fun getKotlinCompilerPath(): String {
        //check the most possible paths
        val pathsToCheck = listOf(
            "/opt/homebrew/bin/kotlinc",
            "/usr/local/bin/kotlinc",
            "/usr/bin/kotlinc",
            System.getenv("KOTLIN_HOME")?.let { "$it/bin/kotlinc" } ?: ""
            //getting env path where kotlin is installed and if not null add it to compiler path
        )

        return pathsToCheck.firstOrNull { File(it).exists() } //if kotlinc in path true
            ?: throw IllegalStateException("Kotlin compiler not found. Please install it.")
    }
}