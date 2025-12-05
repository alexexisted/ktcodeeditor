package alexa.dev.ktcodeeditor.presentation.kotlinc_path_screen

/**
 * @property OnPathUpdated action called when user changes path in field
 * @property OnFindPathClicked action called when user clicks on find the path btn
 * @property OnSaveClicked action called when user click on btn save
 */
sealed interface KotlincAction {

    data class OnPathUpdated(val path: String) : KotlincAction

    object OnFindPathClicked : KotlincAction

    object OnSaveClicked : KotlincAction
}