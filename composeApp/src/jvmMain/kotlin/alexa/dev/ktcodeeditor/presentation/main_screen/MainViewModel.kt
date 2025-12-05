import alexa.dev.ktcodeeditor.domain.*
import alexa.dev.ktcodeeditor.presentation.main_screen.MainUIAction
import alexa.dev.ktcodeeditor.presentation.main_screen.MainUIState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

/**
 * @property executionStateRepository repository to use state with code execution data
 * @property syntaxHighlightStateRepository repository to use state with highlighted words
 * @property codeExecutionService service with logic of code execution
 * @property syntaxHighlightService service with logic of syntax highlighting
 */
class MainViewModel(
    private val executionStateRepository: ExecutionStateRepository,
    private val syntaxHighlightStateRepository: SyntaxHighlightStateRepository,
    private val codeExecutionService: CodeExecutionService,
    private val syntaxHighlightService: SyntaxHighlightService
) : ViewModel() {
    //state flow
    private val _uiState = MutableStateFlow(MainUIState())
    val uiState = _uiState.asStateFlow()

    //global state flow used in service and vm
    val executionUIState: StateFlow<ExecutionUIState> = executionStateRepository.executionUiState
    val syntaxUiState: StateFlow<HighlightUIState> = syntaxHighlightStateRepository.highlightUiState

    private val _uiAction = MutableSharedFlow<MainUIAction>()
    val uiAction = _uiAction.asSharedFlow()

    init {
        //close settings when enter the main screen
        closeSetting()
    }

    //method to operate action calls from ui
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
                highlightWords(action.newText)
            }

            MainUIAction.OnSettingsClicked -> {
                openSettings()
            }
        }
    }

    //change state to show progress circle
    fun showProgress() {
        executionStateRepository.update {
            it.copy(
                isRunning = true
            )
        }
    }

    fun closeSetting() {
        executionStateRepository.update {
            it.copy(
                isRunning = false
            )
        }
    }

    private fun openSettings() {
        _uiState.update {
            it.copy(
                openSettings = true
            )
        }
    }

    //fun to start execution of the script
    fun executeScript() {
        _uiState.update { //update state
            it.copy(
                showTerminal = true,
            )
        }
        //update state of execution repo
        executionStateRepository.update {
            it.copy(
                isRunning = true,
                outputText = ""
            )
        }
        //call execution method from service
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

    //call service to find keywords and highlight them
    private fun highlightWords(text: String) {
        syntaxHighlightService.updateHighlightedText(
            syntaxHighlightService.highlightKotlinSyntax(text)
        )
    }
}

