package alexa.dev.ktcodeeditor.ui.main_screen_components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
/**
 *  @property highlightedText text with highlighted keywords
 *  @property enteredTextSize text size for column calculation
 *  @property enteredText takes text but show it as a transparent
 *  @property onTextChanged callback called when text is changed
 *  @property scrollState syncronised scroll state
 *  @property modifier Modifier
 */
@Composable
fun MainEditorPaneComposable(
    scrollState: ScrollState,
    enteredTextSize: Int,
    highlightedText: AnnotatedString,
    enteredText: String,
    onTextChanged: (newText: String) -> Unit,
    modifier: Modifier
) {

    Row(
        modifier = modifier
            .fillMaxSize()
            .border(2.dp, Color.Gray)
            .padding(8.dp)
    ) {
        //box with line's numbers
        EditorPaneLineNumbersComposable(
            scrollState = scrollState,
            enteredTextSize = enteredTextSize,
        )

        //box with editor pane
        EditorPaneComposable(
            highlightedText = highlightedText,
            enteredText = enteredText,
            onTextChanged = { newText -> onTextChanged(newText) },
            scrollState = scrollState,
            modifier = Modifier.weight(0.7f)
        )
    }

}