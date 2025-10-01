package alexa.dev.ktcodeeditor.domain

import alexa.dev.ktcodeeditor.data.HighlightUIState
import alexa.dev.ktcodeeditor.data.SyntaxHighlightStateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
/**
 * SyntaxHighlightStateRepository implementation
 */
class SyntaxHighlightStateRepositoryImpl: SyntaxHighlightStateRepository {
    private val _highlightUiState = MutableStateFlow(HighlightUIState())
    override val highlightUiState: StateFlow<HighlightUIState> = _highlightUiState.asStateFlow()

    override fun update(transform: (HighlightUIState) -> HighlightUIState) {
        _highlightUiState.update(transform)
    }

}