import alexa.dev.ktcodeeditor.data.ExecutionGlobalUIState
import alexa.dev.ktcodeeditor.data.GlobalUIStateRepository
import alexa.dev.ktcodeeditor.presentation.main_screen.MainUIAction
import alexa.dev.ktcodeeditor.presentation.main_screen.MainUIState
import alexa.dev.ktcodeeditor.service.CodeExecutionService
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

//main place for the logic
class MainViewModel(
    private val globalUIStateRepository: GlobalUIStateRepository,
    private val codeExecutionService: CodeExecutionService
) : ViewModel() {
    //state flow
    private val _uiState = MutableStateFlow(MainUIState())
    val uiState = _uiState.asStateFlow()

    //global state flow used in service and vm
    val globalUIState: StateFlow<ExecutionGlobalUIState> = globalUIStateRepository.globalUiState

    private val _uiAction = MutableSharedFlow<MainUIAction>()
    val uiAction = _uiAction.asSharedFlow()

    fun onAction(action: MainUIAction) {
        when (action) {
            MainUIAction.OnCloseTerminalClicked -> {
                closeTerminal()
            }

            MainUIAction.OnRunScriptClicked -> {
                showProgress()
                executeScript()
            }

            is MainUIAction.OnTextUpdated -> {
                updateText(action.newText)
                updateHighlightedText(highlightKotlinSyntax(action.newText))
            }
        }
    }

    //change state to show progress circle
    fun showProgress() {
        globalUIStateRepository.update {
            it.copy(
                isRunning = true
            )
        }
    }

    //fun to start execution of the script
    fun executeScript() {
        _uiState.update { //update state
            it.copy(
                outputText = "",
                showTerminal = true,
            )
        }
        globalUIStateRepository.update {
            it.copy(
                isRunning = true
            )
        }
        codeExecutionService.executeScript(uiState.value.enteredText)
    }

    //update state to close terminal
    fun closeTerminal() {
        _uiState.update {
            it.copy(
                showTerminal = false
            )
        }
    }

    //update state when user entered code
    fun updateText(text: String) {
        _uiState.update {
            it.copy(
                enteredText = text
            )
        }
    }

    //highlight words
    fun highlightKotlinSyntax(text: String): AnnotatedString {
        val regex = "\\b(${_uiState.value.kotlinKeywords.joinToString("|")})\\b".toRegex()
        return buildAnnotatedString {
            var lastIndex = 0 //track end of last match
            regex.findAll(text).forEach { matchResult -> //find all keywords in text on a pane
                //first and last indexes of matched word
                val start = matchResult.range.first
                val end = matchResult.range.last + 1

                append(text.substring(lastIndex, start)) //firstly append not matched text

                withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                    append(text.substring(start, end)) //style actual matched word
                }

                lastIndex = end //set last index to the end of last matched word
            }
            append(text.substring(lastIndex))
        }
    }

    //highlighted text in state
    fun updateHighlightedText(text: AnnotatedString) {
        _uiState.update {
            it.copy(
                highlightedText = text
            )
        }
    }
}

