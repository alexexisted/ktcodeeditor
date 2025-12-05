package alexa.dev.ktcodeeditor.di

import MainViewModel
import alexa.dev.ktcodeeditor.data.ExecutionStateRepositoryImpl
import alexa.dev.ktcodeeditor.data.KotlincPathRepositoryImpl
import alexa.dev.ktcodeeditor.data.SyntaxHighlightStateRepositoryImpl
import alexa.dev.ktcodeeditor.di.ManualDI.codeExecutionService
import alexa.dev.ktcodeeditor.di.ManualDI.executionStateRepository
import alexa.dev.ktcodeeditor.di.ManualDI.highlightRepository
import alexa.dev.ktcodeeditor.di.ManualDI.kotlincPathFinderService
import alexa.dev.ktcodeeditor.di.ManualDI.kotlincPathRepository
import alexa.dev.ktcodeeditor.di.ManualDI.preferencesStorage
import alexa.dev.ktcodeeditor.di.ManualDI.syntaxHighlightService
import alexa.dev.ktcodeeditor.domain.*
import alexa.dev.ktcodeeditor.domain.storage.PreferencesStorage
import alexa.dev.ktcodeeditor.presentation.kotlinc_path_screen.KotlincPathViewModel

/**
 * Dependency injection singleton object, creates:
 * @property executionStateRepository
 * @property highlightRepository
 * @property codeExecutionService
 * @property syntaxHighlightService
 * @property preferencesStorage
 * @property kotlincPathRepository
 * @property kotlincPathFinderService
 */
object ManualDI {
    val executionStateRepository: ExecutionStateRepository by lazy {
        ExecutionStateRepositoryImpl()
    }

    val highlightRepository: SyntaxHighlightStateRepository by lazy {
        SyntaxHighlightStateRepositoryImpl()
    }

    val preferencesStorage: PreferencesStorage by lazy {
        PreferencesStorage()
    }

    val kotlincPathRepository: KotlincPathRepository by lazy {
        KotlincPathRepositoryImpl(preferencesStorage)
    }

    val kotlincPathFinderService: KotlincPathFinderService by lazy {
        KotlincPathFinderService(executionStateRepository)
    }

    val codeExecutionService: CodeExecutionService by lazy {
        CodeExecutionService(executionStateRepository, kotlincPathRepository)
    }

    val syntaxHighlightService: SyntaxHighlightService by lazy {
        SyntaxHighlightService(highlightRepository)
    }

    //put all objects into view model to ensure single instances for one view models
    fun createMainViewModel(): MainViewModel {
        return MainViewModel(
            executionStateRepository = executionStateRepository,
            codeExecutionService = codeExecutionService,
            syntaxHighlightService = syntaxHighlightService,
            syntaxHighlightStateRepository = highlightRepository
        )
    }

    fun createKotlincPathViewModel(): KotlincPathViewModel {
        return KotlincPathViewModel(
            kotlincPathFinderService = kotlincPathFinderService,
            kotlincPathRepository = kotlincPathRepository,
            executionStateRepository = executionStateRepository
        )
    }
}