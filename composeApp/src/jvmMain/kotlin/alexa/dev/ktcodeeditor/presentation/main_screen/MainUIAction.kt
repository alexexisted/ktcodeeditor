package alexa.dev.ktcodeeditor.presentation.main_screen

/**
 * @property OnTextUpdated action called when entered new text
 * @property OnRunScriptClicked action called when run script is clicked
 * @property OnCloseTerminalClicked action called when close terminal is clicked
 * @property OnSettingsClicked action called when user clicks to open a terminal
 */
sealed interface MainUIAction {
    data class OnTextUpdated(val newText: String) : MainUIAction

    object OnRunScriptClicked : MainUIAction

    object OnCloseTerminalClicked : MainUIAction

    object OnSettingsClicked : MainUIAction
}