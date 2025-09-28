package alexa.dev.ktcodeeditor.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun RunButtonComposable(
    isRunning: Boolean,
    onRunClicked: () -> Unit,
) {
    Button(
        onClick = {
            onRunClicked()
        },
        enabled = !isRunning
    ) {
        Text(if (isRunning) "Running..." else "Run Script")
    }
}