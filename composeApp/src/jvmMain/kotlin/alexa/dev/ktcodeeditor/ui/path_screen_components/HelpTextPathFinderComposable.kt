package alexa.dev.ktcodeeditor.ui.path_screen_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HelpTextPathFinderComposable() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "How to find kotlinc:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "• Linux/Mac: Run 'which kotlinc' in terminal",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "• Windows: Run 'where kotlinc' in command prompt",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "• Or browse to your Kotlin installation directory",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}