package alexa.dev.ktcodeeditor.domain

/**
 *  Repository describes crud operations with data
 */
interface KotlincPathRepository {

    fun savePath(path: String)

    fun getPath(): String?

    fun clearPath()
}

