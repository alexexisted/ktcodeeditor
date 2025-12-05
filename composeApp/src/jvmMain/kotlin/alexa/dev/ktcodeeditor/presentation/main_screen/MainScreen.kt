import alexa.dev.ktcodeeditor.presentation.main_screen.MainUIAction
import alexa.dev.ktcodeeditor.ui.main_screen_components.MainEditorPaneComposable
import alexa.dev.ktcodeeditor.ui.main_screen_components.PanelWithButtonsComposable
import alexa.dev.ktcodeeditor.ui.main_screen_components.TerminalComposable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ktcodeeditor.composeapp.generated.resources.Res
import ktcodeeditor.composeapp.generated.resources.setting_icon
import org.jetbrains.compose.resources.painterResource

/**
 * @param viewModel takes viewModel instance created via manual di
 */
@Composable
fun MainScreen(onSettingsClicked: () -> Unit = {}, viewModel: MainViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val executionUIState by viewModel.executionUIState.collectAsState()
    val syntaxUIState by viewModel.syntaxUiState.collectAsState()
    val scrollState = rememberScrollState() //shared scroll state for line's numbers and code-editor itself

    LaunchedEffect(uiState.openSettings) {
        if (uiState.openSettings) {
            onSettingsClicked()
        }
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        //header and settings button
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Code Editor", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            IconButton(
                onClick = { viewModel.onAction(MainUIAction.OnSettingsClicked) }
            ) {
                Icon(
                    painter = painterResource(Res.drawable.setting_icon),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // contains two parts inside, column with lines numbers and text input
        MainEditorPaneComposable(
            scrollState = scrollState,
            enteredTextSize = uiState.enteredText.lines().size,
            highlightedText = syntaxUIState.highlightedText,
            enteredText = uiState.enteredText,
            onTextChanged = { newText -> viewModel.onAction(MainUIAction.OnTextUpdated(newText)) },
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        //row with buttons and progress circle
        PanelWithButtonsComposable(
            isRunning = executionUIState.isRunning,
            showTerminal = uiState.showTerminal,
            lastExitCode = executionUIState.exitCode,
            onRunCodeClicked = { viewModel.onAction(MainUIAction.OnRunScriptClicked) },
            onCloseTerminalClicked = { viewModel.onAction(MainUIAction.OnCloseTerminalClicked) }
        )

        //terminal output part
        if (uiState.showTerminal) {
            TerminalComposable(
                outputText = executionUIState.outputText
            )
        }
    }
}
