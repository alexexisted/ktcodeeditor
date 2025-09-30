package alexa.dev.ktcodeeditor.domain

import alexa.dev.ktcodeeditor.data.ExecutionGlobalUIState
import alexa.dev.ktcodeeditor.data.GlobalUIStateRepository
import alexa.dev.ktcodeeditor.presentation.main_screen.MainUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GlobalRepositoryImpl: GlobalUIStateRepository {
    private val _globalUiState = MutableStateFlow(ExecutionGlobalUIState())
    override val globalUiState: StateFlow<ExecutionGlobalUIState> = _globalUiState.asStateFlow()

    override fun update(transform: (ExecutionGlobalUIState) -> ExecutionGlobalUIState) {
        _globalUiState.update(transform)
    }
}