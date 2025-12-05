package alexa.dev.ktcodeeditor.navigation

import MainScreen
import MainViewModel
import alexa.dev.ktcodeeditor.presentation.kotlinc_path_screen.KotlinCompilerPathScreen
import alexa.dev.ktcodeeditor.presentation.kotlinc_path_screen.KotlincPathViewModel
import androidx.compose.runtime.*


private enum class Route { KotlincPath, Main }

/**
 *  Navigation host to navigate through screeens
 *  @property MainViewModel
 *  @property KotlincPathViewModel
 */
@Composable
fun AppNav(mainViewModel: MainViewModel, kotlincPathViewModel: KotlincPathViewModel) {
    var route by remember { mutableStateOf(Route.KotlincPath) }

    when (route) {
        Route.KotlincPath -> KotlinCompilerPathScreen(
            viewModel = kotlincPathViewModel,
            onContinueToMain = { route = Route.Main }
        )

        Route.Main -> MainScreen(mainViewModel)
    }
}