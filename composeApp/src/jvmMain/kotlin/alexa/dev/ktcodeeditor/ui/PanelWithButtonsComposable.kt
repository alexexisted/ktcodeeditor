package alexa.dev.ktcodeeditor.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PanelWithButtonsComposable(
    isRunning: Boolean,
    showTerminal: Boolean,
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

        //close ternminal button
        if (showTerminal) {
            TerminalButtonComposable(
                onCloseTerminalClicked = { onCloseTerminalClicked() }
            )
        }
    }
}