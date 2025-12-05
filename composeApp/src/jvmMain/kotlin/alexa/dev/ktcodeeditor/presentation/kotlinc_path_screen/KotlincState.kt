package alexa.dev.ktcodeeditor.presentation.kotlinc_path_screen

/**
 * @property path takes text entered by user
 * @property isPathValid holds boolean value is path valid
 * @property showError holds boolean value to show the error
 * @property isDefaultPathValid if true, then by default we navigate to main screen
 */
data class KotlincState(
    val path: String = "",
    val isPathValid: Boolean = false,
    val showError: Boolean = false,
    val isDefaultPathValid: Boolean = false
)