package alexa.dev.ktcodeeditor.domain.storage

import java.util.prefs.Preferences

/**
 * Storage to store the data about the path of kotlinc
 */
class PreferencesStorage {
    private val prefs = Preferences.userRoot().node("ktcodeeditor")

    fun saveKotlincPath(path: String) {
        prefs.put(KEY_KOTLINC_PATH, path)
        prefs.flush()
    }

    fun loadKotlincPath(): String? {
        return prefs.get(KEY_KOTLINC_PATH, null)
    }

    fun clearKotlincPath() {
        prefs.remove(KEY_KOTLINC_PATH)
        prefs.flush()
    }

    companion object {
        private const val KEY_KOTLINC_PATH = "kotlinc_path"
    }
}