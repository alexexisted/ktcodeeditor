package alexa.dev.ktcodeeditor.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
/**
 *  @property isRunning is code currently executing
 *  @property onRunClicked callback called to start code execution
 */
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