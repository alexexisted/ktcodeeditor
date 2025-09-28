package alexa.dev.ktcodeeditor.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditorPaneComposable(
    highlightedText: AnnotatedString,
    enteredText: String,
    onTextChanged: (newText: String) -> Unit,
    scrollState: ScrollState,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(5.dp)
    ) {

        Text( //display highlighted text
            text = highlightedText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            fontFamily = FontFamily.Monospace,
            color = Color.Black,
            letterSpacing = 2.sp,
            modifier = Modifier.fillMaxWidth()
        )
        //get user's input but make it transparent
        BasicTextField(
            value = enteredText,
            onValueChange = { newText ->
                onTextChanged(newText)
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = Color.Transparent,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 2.sp,
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}