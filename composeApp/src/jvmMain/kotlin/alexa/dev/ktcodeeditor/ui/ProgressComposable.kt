package alexa.dev.ktcodeeditor.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressComposable() {

    CircularProgressIndicator(
        modifier = Modifier.size(44.dp),
        strokeWidth = 4.dp,
        color = Color.Green
    )
}