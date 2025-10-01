package alexa.dev.ktcodeeditor.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
/**
 *  @property isRunning is code currently executing
 *  @property showTerminal do we need to show terminal
 *  @property lastExitCode exit code of last run
 *  @property onRunCodeClicked callback called when we run the code
 *  @property onCloseTerminalClicked callback when we close terminal
 */
@Composable
fun PanelWithButtonsComposable(
    isRunning: Boolean,
    showTerminal: Boolean,
    lastExitCode: String?,
    onRunCodeClicked: () -> Unit,
    onCloseTerminalClicked: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        //run script button
        RunButtonComposable(
            isRunning = isRunning,
            onRunClicked = {
                onRunCodeClicked()
            }
        )
        //progress circle
        if (isRunning) {
            Spacer(modifier = Modifier.width(8.dp))
            ProgressComposable()
        }

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.width(8.dp))
        Text("Last exit code: ${lastExitCode ?: "none"}")

        //close ternminal button
        if (showTerminal) {
            TerminalButtonComposable(
                onCloseTerminalClicked = { onCloseTerminalClicked() }
            )
        }
    }
}