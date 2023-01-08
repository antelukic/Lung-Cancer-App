package com.lukic.data.di

import com.lukic.data.api.KtorClient
import org.koin.dsl.module

val networkModule = module {

    single { KtorClient("Add in future", "Add in future") }
}
