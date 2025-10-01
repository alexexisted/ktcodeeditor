package alexa.dev.ktcodeeditor.service

import alexa.dev.ktcodeeditor.data.SyntaxHighlightStateRepository
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
/**
 *  @property syntaxHighlightStateRepository repository that contains state object of highlighted words
 */
class SyntaxHighlightService(
    val syntaxHighlightStateRepository: SyntaxHighlightStateRepository
) {
    //set of keywords that we highlight, now have done with regex to compile it only once with class creation
    private val keywordRegex: Regex = run {
        val keywords = setOf(
            "val", "var", "fun", "if", "else", "for", "while", "when",
            "class", "object", "return", "break", "continue",
            "try", "catch", "finally", "import", "print", "String", "Int",
            "List", "Thread", "System", "println"
        )
        "\\b(${keywords.joinToString("|")})\\b".toRegex() //join to string immideatly
    }

    //reusable style object, moved separate, to be created only once
    private val keywordStyle = SpanStyle(
        color = Color.Blue,
        fontWeight = FontWeight.Bold
    )


    //highlight words
    fun highlightKotlinSyntax(text: String): AnnotatedString {
        return buildAnnotatedString {
            var lastIndex = 0 //track end of last match
            //find all keywords in text and go through each
            keywordRegex.findAll(text).forEach { matchResult ->
                //first and last indexes of matched word
                val start = matchResult.range.first
                //first ind of matched word
                val end = matchResult.range.last + 1
                //last ind of matched word, +1 because substrings add exclusive index

                if (start > lastIndex) {
                    /**
                     * if we have case like "private val x = 5",
                     * where private is not meant to be highlighted, then we have
                     * last index = 0 and start = 8, so 8>0, so we put part before start as a
                     * plain text
                     */

                    append(text.substring(lastIndex, start))
                }

                withStyle(keywordStyle) {
                    //then we add matched word
                    append(text.substring(start, end))
                }

                lastIndex = end //and set last index to the end of last matched word
            }
            if (lastIndex < text.length) { //append remaining part
                append(text.substring(lastIndex))
            }
        }
    }

    //highlighted text updates in state
    fun updateHighlightedText(text: AnnotatedString) {
        syntaxHighlightStateRepository.update {
            it.copy(
                highlightedText = text
            )
        }
    }
}