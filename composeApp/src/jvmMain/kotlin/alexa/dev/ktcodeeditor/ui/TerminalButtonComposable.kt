package alexa.dev.ktcodeeditor.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 *  @property onCloseTerminalClicked callback when we close terminal
 */
@Composable
fun TerminalButtonComposable(
    onCloseTerminalClicked: () -> Unit,

) {
    Button(
        onClick = { onCloseTerminalClicked() },
        modifier = Modifier.padding(8.dp)
    ) {
        Text("Close Terminal", color = Color.White)
    }
}