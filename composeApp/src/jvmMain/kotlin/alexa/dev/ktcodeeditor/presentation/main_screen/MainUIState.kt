package alexa.dev.ktcodeeditor.presentation.main_screen
/**
 * @property enteredText takes text entered by user
 * @property showTerminal shows when do we need to show terminal
 */
data class MainUIState(
    var enteredText: String = "",
    val showTerminal: Boolean = false,
    val openSettings: Boolean = false
)

