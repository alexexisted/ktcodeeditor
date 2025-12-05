package alexa.dev.ktcodeeditor.domain

import java.io.File

/**
 * Validates default paths from user
 * returns the kotlinc path if it exists and is executable
 * @property executionStateRepository
 * @return validated path
 */
class KotlincPathFinderService(
    val executionStateRepository: ExecutionStateRepository
) {
    fun getKotlincDefaultPath(): Result<String> {
        val pathsToCheck = listOf(
            "/opt/homebrew/bin/kotlinc",
            "/usr/local/bin/kotlinc",
            "/usr/bin/kotlinc",
            System.getenv("KOTLIN_HOME")?.let { "$it/bin/kotlinc" } ?: ""
        )

        val foundPath = pathsToCheck.firstOrNull { it.isNotEmpty() && File(it).exists() }

        val file = File(foundPath ?: "")
        if (foundPath != null && file.canExecute()) {
            updatePathInState(foundPath)
            return Result.success(foundPath)

        } else {
            return Result.failure(
                IllegalArgumentException(
                    "Kotlin compiler not found. Please install it."
                )
            )
        }
    }

    fun getPathFromUser(path: String): Result<String> {
        val file = File(path)

        return when {
            !file.exists() -> {
                Result.failure(
                    IllegalArgumentException("Kotlin compiler not found at: $path")
                )
            }

            !file.canExecute() -> {
                Result.failure(
                    IllegalArgumentException("File is not executable: $path")
                )
            }

            file.name != "kotlinc" && file.name != "kotlinc.bat" -> {
                Result.failure(
                    IllegalArgumentException("Not a valid kotlinc executable: ${file.name}")
                )
            }

            else -> {
                updatePathInState(path)
                Result.success(path)
            }
        }
    }

    private fun updatePathInState(path: String) {
        executionStateRepository.update {
            it.copy(
                kotlincPath = path
            )
        }
    }
}