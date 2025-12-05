package alexa.dev.ktcodeeditor.presentation.kotlinc_path_screen

import alexa.dev.ktcodeeditor.ui.path_screen_components.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ktcodeeditor.composeapp.generated.resources.Res
import ktcodeeditor.composeapp.generated.resources.clear_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun KotlinCompilerPathScreen(
    viewModel: KotlincPathViewModel = viewModel(),
    onContinueToMain: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderPathFinderComposable()

        Spacer(modifier = Modifier.height(16.dp))

        DescriptionPathFinderComposable()

        Spacer(modifier = Modifier.height(8.dp))

        //field to enter the path
        OutlinedTextField(
            value = uiState.path,
            onValueChange = { viewModel.onAction(KotlincAction.OnPathUpdated(it)) },
            label = { Text("Kotlinc Path") },
            placeholder = { Text("e.g., /usr/local/bin/kotlinc") },
            modifier = Modifier.fillMaxWidth(),
            isError = uiState.showError,
            supportingText = {
                when {
                    uiState.showError -> Text(
                        text = "Invalid path. Please select a valid kotlinc executable",
                        color = MaterialTheme.colorScheme.error
                    )

                    uiState.isPathValid -> Text(
                        text = "✓ Valid kotlinc path",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
            trailingIcon = {
                if (uiState.path.isNotEmpty()) {
                    IconButton(onClick = { viewModel.onAction(KotlincAction.OnPathUpdated("")) }) {
                        Icon(
                            painter = painterResource(Res.drawable.clear_icon),
                            contentDescription = null
                        )
                    }
                }
            },
            singleLine = true
        )

        BrowseButtonPathFinderComposable(
            onBrowseClicked = { viewModel.onAction(KotlincAction.OnFindPathClicked) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        HelpTextPathFinderComposable()

        Spacer(modifier = Modifier.weight(1f))

        SaveButtonPathFinderComposable(
            isPathValid = uiState.isPathValid,
            onPathSelected = {
                viewModel.onAction(KotlincAction.OnSaveClicked)
                onContinueToMain()
            }
        )
    }
}