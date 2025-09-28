import alexa.dev.ktcodeeditor.ui.EditorPaneComposable
import alexa.dev.ktcodeeditor.ui.EditorPaneLineNumbersComposable
import alexa.dev.ktcodeeditor.ui.ProgressComposable
import alexa.dev.ktcodeeditor.ui.RunButtonComposable
import alexa.dev.ktcodeeditor.ui.TerminalButtonComposable
import alexa.dev.ktcodeeditor.ui.TerminalComposable
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
    val scrollState = rememberScrollState() //shared scroll state for line's numbers and code-editor itself

    Column(
        modifier = Modifier
            .height(920.dp)
            .width(1080.dp)
            .padding(16.dp)
    ) {
        Text("Code Editor", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, Color.Gray)
                .padding(8.dp)
                .weight(1f)
        ) {
            //box with line's numbers
            EditorPaneLineNumbersComposable(
                scrollState = scrollState,
                enteredTextSize = uiState.enteredText.lines().size
            )
            //box with editor pane
            EditorPaneComposable(
                highlightedText = uiState.highlightedText,
                enteredText = uiState.enteredText,
                onTextChanged = { newText -> viewModel.updateText(newText) },
                highlightText = { newText -> viewModel.updateHighlightedText(viewModel.highlightKotlinSyntax(newText)) },
                scrollState = scrollState
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        //row with buttons and progress circle
        Row(verticalAlignment = Alignment.CenterVertically) {
            //run script button
            RunButtonComposable(
                isRunning = uiState.isRunning,
                onRunClicked = {
                    viewModel.showProgress()
                    viewModel.executeScript()
                }
            )
            //progress circle
            if (uiState.isRunning) {
                Spacer(modifier = Modifier.width(8.dp))
                ProgressComposable()
            }

            Spacer(modifier = Modifier.weight(1f))

            //close ternminal button
            if (uiState.showTerminal) {
                TerminalButtonComposable(
                    onCloseTerminalClicked = { viewModel.closeTerminal() }
                )
            }
        }

        //terminal output part
        if (uiState.showTerminal) {
            TerminalComposable(
                outputText = uiState.outputText
            )
        }
    }
}
