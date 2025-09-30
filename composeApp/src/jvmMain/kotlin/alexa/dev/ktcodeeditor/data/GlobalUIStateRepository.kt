package alexa.dev.ktcodeeditor.data

import kotlinx.coroutines.flow.StateFlow

interface GlobalUIStateRepository {
    val globalUiState: StateFlow<ExecutionGlobalUIState>

    fun update(transform: (ExecutionGlobalUIState) -> ExecutionGlobalUIState)
}

data class ExecutionGlobalUIState(
    val outputText: String = "",
    val exitCode: String = "",
    val isRunning: Boolean = false
)