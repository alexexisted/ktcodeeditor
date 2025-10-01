package alexa.dev.ktcodeeditor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
/**
 *  @property outputText takes output to show it in terminal
 */
@Composable
fun TerminalComposable(
    outputText: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Black)
            .padding(8.dp)
    ) {

        //using lazy to ensure that scroll state saves after recomposition
        val listState = rememberLazyListState()

        //selection container t oallow user copy output/error
        SelectionContainer {
            LazyColumn(
                state = listState,
                modifier = Modifier,
            ) {
                //displaying each line separately
                items(outputText.lines()) { line ->
                    Text(
                        text = line,
                        color = Color.Green,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 0.5.sp,
                    )
                }
            }
        }

        //when output updates terminal scrolls to the last item
        LaunchedEffect(outputText) {
            listState.scrollToItem(outputText.lines().size - 1)
        }
    }
}