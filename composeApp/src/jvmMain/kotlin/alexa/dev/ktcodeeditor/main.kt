package alexa.dev.ktcodeeditor

import MainScreen
import alexa.dev.ktcodeeditor.di.ManualDI
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
/**
 *  Entrance point of the app
 */
fun main() = application {
    // have to do manual di to pass singletones for vm and service
    val mainViewModel = ManualDI.createMainViewModel()

    Window(
        onCloseRequest = ::exitApplication,
        title = "ktcodeeditor",
    ) {
        MainScreen(mainViewModel)
    }
}