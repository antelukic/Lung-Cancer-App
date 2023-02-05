package com.lukic.di

import android.app.Application
import android.content.res.Resources
import org.koin.dsl.module

fun appModule(application: Application) = module {
    single { application }
    single<Resources> { get<Application>().resources }
}
