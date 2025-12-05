package alexa.dev.ktcodeeditor.presentation.kotlinc_path_screen

import alexa.dev.ktcodeeditor.domain.ExecutionStateRepository
import alexa.dev.ktcodeeditor.domain.KotlincPathFinderService
import alexa.dev.ktcodeeditor.domain.KotlincPathRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KotlincPathViewModel(
    private val kotlincPathFinderService: KotlincPathFinderService,
    private val kotlincPathRepository: KotlincPathRepository,
    private val executionStateRepository: ExecutionStateRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(KotlincState())
    val uiState = _uiState.asStateFlow()

    init {
        checkDefaultPaths()
    }

    //actions called from ui
    fun onAction(action: KotlincAction) {
        when (action) {
            KotlincAction.OnFindPathClicked -> {
                checkPathFromUser()
            }

            is KotlincAction.OnPathUpdated -> {
                onPathChanged(action.path)
            }

            KotlincAction.OnSaveClicked -> {
                savePath()
            }
        }
    }

    //check default paths on a vm creation
    private fun checkDefaultPaths() {
        val result = kotlincPathFinderService.getKotlincDefaultPath()
        if (result.isSuccess) {
            _uiState.update {
                it.copy(
                    path = result.getOrDefault(""),
                    isPathValid = true,
                    showError = false,
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    path = "",
                    isPathValid = false,
                    showError = true,
                )
            }
        }

    }

    //checks path given by user
    private fun checkPathFromUser() {
        val result = kotlincPathFinderService.getPathFromUser(uiState.value.path)
        if (result.isSuccess && result.getOrNull() != null) {
            _uiState.update {
                it.copy(
                    path = result.getOrDefault(""),
                    isPathValid = true,
                    showError = false
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    path = "",
                    isPathValid = false,
                    showError = true
                )
            }
        }
    }

    //updates path
    private fun onPathChanged(path: String) {
        _uiState.update {
            it.copy(
                path = path,
                showError = false
            )
        }
    }

    //saves path in storage
    private fun savePath() {
        kotlincPathRepository.savePath(uiState.value.path)
        executionStateRepository.update {
            it.copy(
                isSettingsOpen = false
            )
        }
    }
}