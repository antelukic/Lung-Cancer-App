package com.lukic.presentation.screen.intro.di

import com.lukic.domain.intro.resources.IntroTranslations
import com.lukic.presentation.screen.intro.IntroViewModel
import com.lukic.presentation.screen.intro.resources.IntroTranslationsImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val introModule = module {

    viewModel { IntroViewModel(introTranslations = get()) }

    single<IntroTranslations> { IntroTranslationsImpl(resources = get()) }
}
