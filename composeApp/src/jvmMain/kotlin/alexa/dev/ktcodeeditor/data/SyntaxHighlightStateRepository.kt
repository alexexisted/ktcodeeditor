package alexa.dev.ktcodeeditor.data

import androidx.compose.ui.text.AnnotatedString
import kotlinx.coroutines.flow.StateFlow
/**
 *  Repository contains state of highlighted words and method to update them
 */
interface SyntaxHighlightStateRepository {
    val highlightUiState: StateFlow<HighlightUIState>

    fun update(transform: (HighlightUIState) -> HighlightUIState)

}

/**
 *  state for highlighted keywords
 */
data class HighlightUIState(
    val highlightedText: AnnotatedString = AnnotatedString("")
)