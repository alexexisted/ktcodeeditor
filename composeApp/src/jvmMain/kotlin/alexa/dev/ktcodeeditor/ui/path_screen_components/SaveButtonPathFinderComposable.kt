package alexa.dev.ktcodeeditor.ui.path_screen_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SaveButtonPathFinderComposable(
    isPathValid: Boolean,
    onPathSelected: () -> Unit,
) {
    Button(
        onClick = {
            if (isPathValid) {
                onPathSelected()
            }
        },
        enabled = isPathValid,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text("Save path or exit")
    }
}