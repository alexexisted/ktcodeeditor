package alexa.dev.ktcodeeditor.di

import MainViewModel
import alexa.dev.ktcodeeditor.data.GlobalUIStateRepository
import alexa.dev.ktcodeeditor.domain.GlobalRepositoryImpl
import alexa.dev.ktcodeeditor.service.CodeExecutionService

object ManualDI {
    val globalUIStateRepository: GlobalUIStateRepository by lazy {
        GlobalRepositoryImpl()
    }

    val codeExecutionService: CodeExecutionService by lazy {
        CodeExecutionService(globalUIStateRepository)
    }

    fun createMainViewModel(): MainViewModel {
        return MainViewModel(globalUIStateRepository, codeExecutionService)
    }
}