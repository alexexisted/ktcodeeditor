package alexa.dev.ktcodeeditor.data

import kotlinx.coroutines.flow.StateFlow

/**
 * Interface used to hold and update state of executed code
 *
 * The transform function is responsible for state updates
 *
 * @property executionUiState Flow of execution state for observing changes
 */
interface ExecutionStateRepository {
    val executionUiState: StateFlow<ExecutionUIState>

    fun update(transform: (ExecutionUIState) -> ExecutionUIState)
}

/**
 *  State for executed code contains info:
 *
 *  @property outputText For life updates of execution
 *  @property exitCode To track last exit code or set it to none
 *  @property isRunning To track if code is currently running
 */
data class ExecutionUIState(
    val outputText: String = "",
    val exitCode: String = "none",
    val isRunning: Boolean = false
)