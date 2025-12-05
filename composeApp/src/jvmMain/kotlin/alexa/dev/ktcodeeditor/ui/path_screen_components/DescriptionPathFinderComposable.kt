package alexa.dev.ktcodeeditor.ui.path_screen_components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DescriptionPathFinderComposable() {
    Text(
        text = "Please, configure the path to your Kotlin compiler.",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}