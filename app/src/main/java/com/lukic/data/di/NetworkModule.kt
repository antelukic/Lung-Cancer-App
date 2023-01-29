package com.lukic.data.di

import com.lukic.data.api.KtorClient
import com.lukic.data.api.LungCancerService
import com.lukic.data.api.LungCancerServiceImpl
import com.lukic.data.parser.ResultParser
import com.lukic.data.parser.ResultParserImpl
import org.koin.dsl.module

fun networkModule(baseUrl: String, apiKey: String) = module {
    single { KtorClient(baseUrl, apiKey).client }

    single<LungCancerService> { LungCancerServiceImpl(client = get()) }

    single<ResultParser> { ResultParserImpl() }
}
