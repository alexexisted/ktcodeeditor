package alexa.dev.ktcodeeditor.ui.path_screen_components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ktcodeeditor.composeapp.generated.resources.Res
import ktcodeeditor.composeapp.generated.resources.search_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun BrowseButtonPathFinderComposable(
    onBrowseClicked: () -> Unit,
) {
    Button(
        onClick = { onBrowseClicked() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(Res.drawable.search_icon),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Browse for kotlinc")
    }
}