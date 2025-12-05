package alexa.dev.ktcodeeditor.data

import alexa.dev.ktcodeeditor.domain.KotlincPathRepository
import alexa.dev.ktcodeeditor.domain.storage.PreferencesStorage

/**
 * KotlincPathRepository implementation
 */
class KotlincPathRepositoryImpl(
    private val storage: PreferencesStorage
) : KotlincPathRepository {

    override fun savePath(path: String) {
        storage.saveKotlincPath(path)
    }

    override fun getPath(): String? {
        return storage.loadKotlincPath()
    }

    override fun clearPath() {
        storage.clearKotlincPath()
    }

}