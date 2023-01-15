package com.lukic.presentation.screen.userdetails.di

import com.lukic.data.userdetails.repository.UserRepositoryImpl
import com.lukic.domain.repository.UserRepository
import com.lukic.domain.usecase.PublishUserInputStepUseCase
import com.lukic.domain.usecase.PublishUserStepInfoUseCase
import com.lukic.domain.usecase.QueryUserInputStepUseCase
import com.lukic.presentation.screen.userdetails.InputInfoViewModel
import com.lukic.presentation.screen.userdetails.resources.InputInfoTranslations
import com.lukic.presentation.screen.userdetails.resources.InputInfoTranslationsImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userInputModule = module {
    single<InputInfoTranslations> { InputInfoTranslationsImpl(get()) }

    viewModel {
        InputInfoViewModel(
            inputInfoTranslations = get(),
            publishUserInputStep = get(),
            publishUserStepInfo = get(),
            userInputStep = get()
        )
    }

    single { PublishUserStepInfoUseCase(get()) }
    single { PublishUserInputStepUseCase(get()) }
    single { QueryUserInputStepUseCase(get()) }

    single<UserRepository> { UserRepositoryImpl() }
}
