package com.lukic.presentation.screen.results.di

import com.lukic.domain.usecase.QueryResultsUseCase
import com.lukic.presentation.screen.results.ResultsViewModel
import com.lukic.presentation.screen.results.resources.ResultTranslations
import com.lukic.presentation.screen.results.resources.ResultTranslationsImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val resultModule = module {

    single { QueryResultsUseCase(get()) }

    single<ResultTranslations> { ResultTranslationsImpl(get()) }

    viewModel {
        ResultsViewModel(
            userResult = get(),
            translations = get()
        )
    }
}
