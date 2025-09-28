package alexa.dev.ktcodeeditor.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditorPaneLineNumbersComposable(
    scrollState: ScrollState,
    enteredTextSize: Int,
) {

    Box(
        modifier = Modifier
            .width(50.dp)
            .padding(top = 5.dp)
            .verticalScroll(scrollState)
    ) {
        Column {
            // set line numbers according to existed lines
            for (i in 1..enteredTextSize.coerceAtLeast(1)) {
                Text(
                    text = "$i", //set line count
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 2.sp,
                )
            }
        }
    }
}