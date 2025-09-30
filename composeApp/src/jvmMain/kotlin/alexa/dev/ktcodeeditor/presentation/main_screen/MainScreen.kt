import alexa.dev.ktcodeeditor.presentation.main_screen.MainUIAction
import alexa.dev.ktcodeeditor.ui.*
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val globalUIState by viewModel.globalUIState.collectAsState()
    val scrollState = rememberScrollState() //shared scroll state for line's numbers and code-editor itself

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text("Code Editor", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        // contains two parts inside, column with lines numbers and text input
        MainEditorPaneComposable(
            scrollState = scrollState,
            enteredTextSize = uiState.enteredText.lines().size,
            highlightedText = uiState.highlightedText,
            enteredText = uiState.enteredText,
            onTextChanged = { newText -> viewModel.onAction(MainUIAction.OnTextUpdated(newText)) },
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        //row with buttons and progress circle
        PanelWithButtonsComposable(
            isRunning = globalUIState.isRunning,
            showTerminal = uiState.showTerminal,
            onRunCodeClicked = {viewModel.onAction(MainUIAction.OnRunScriptClicked)},
            onCloseTerminalClicked = {viewModel.onAction(MainUIAction.OnCloseTerminalClicked)}
        )

        //terminal output part
        if (uiState.showTerminal) {
            TerminalComposable(
                outputText = globalUIState.outputText
            )
        }
    }
}
