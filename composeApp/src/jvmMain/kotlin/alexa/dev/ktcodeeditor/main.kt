package alexa.dev.ktcodeeditor

import alexa.dev.ktcodeeditor.di.ManualDI
import alexa.dev.ktcodeeditor.navigation.AppNav
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

/**
 *  Entrance point of the app
 */
fun main() = application {
    //had to do manual di to pass singletones for vm and service
    val mainViewModel = ManualDI.createMainViewModel()
    val kotlincViewModel = ManualDI.createKotlincPathViewModel()

    Window(
        onCloseRequest = ::exitApplication,
        title = "ktcodeeditor",
    ) {
        AppNav(mainViewModel = mainViewModel, kotlincPathViewModel = kotlincViewModel)
    }
}
