package alexa.dev.ktcodeeditor.di

import MainViewModel
import alexa.dev.ktcodeeditor.data.ExecutionStateRepository
import alexa.dev.ktcodeeditor.data.SyntaxHighlightStateRepository
import alexa.dev.ktcodeeditor.domain.ExecutionStateRepositoryImpl
import alexa.dev.ktcodeeditor.domain.SyntaxHighlightStateRepositoryImpl
import alexa.dev.ktcodeeditor.service.CodeExecutionService
import alexa.dev.ktcodeeditor.service.SyntaxHighlightService

/**
 * Dependency injection singleton object, creates:
 * @property executionStateRepository
 * @property highlightRepository
 * @property codeExecutionService
 * @property syntaxHighlightService
 */
object ManualDI {
    val executionStateRepository: ExecutionStateRepository by lazy {
        ExecutionStateRepositoryImpl()
    }

    val highlightRepository: SyntaxHighlightStateRepository by lazy {
        SyntaxHighlightStateRepositoryImpl()
    }

    val codeExecutionService: CodeExecutionService by lazy {
        CodeExecutionService(executionStateRepository)
    }

    val syntaxHighlightService: SyntaxHighlightService by lazy {
        SyntaxHighlightService(highlightRepository)
    }
    //put all objects into view model to ensure single instances for one view model
    fun createMainViewModel(): MainViewModel {
        return MainViewModel(
            executionStateRepository = executionStateRepository,
            codeExecutionService = codeExecutionService,
            syntaxHighlightService = syntaxHighlightService,
            syntaxHighlightStateRepository = highlightRepository
        )
    }
}