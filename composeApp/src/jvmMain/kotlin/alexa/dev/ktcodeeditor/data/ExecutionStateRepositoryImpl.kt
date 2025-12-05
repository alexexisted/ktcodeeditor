package alexa.dev.ktcodeeditor.data

import alexa.dev.ktcodeeditor.domain.ExecutionUIState
import alexa.dev.ktcodeeditor.domain.ExecutionStateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
/**
 * ExecutionStateRepository implementation
 */
class ExecutionStateRepositoryImpl: ExecutionStateRepository {
    private val _executionUiState = MutableStateFlow(ExecutionUIState())
    override val executionUiState: StateFlow<ExecutionUIState> = _executionUiState.asStateFlow()

    override fun update(transform: (ExecutionUIState) -> ExecutionUIState) {
        _executionUiState.update(transform)
    }
}