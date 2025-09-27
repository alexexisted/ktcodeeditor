package alexa.dev.ktcodeeditor

import MainScreen
import MainViewModel
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ktcodeeditor",
    ) {
        MainScreen()
    }
}