package alexa.dev.ktcodeeditor.presentation.main_screen

sealed interface MainUIAction {
    data class OnTextUpdated(val newText: String) : MainUIAction

    object OnRunScriptClicked : MainUIAction

    object OnCloseTerminalClicked : MainUIAction
}