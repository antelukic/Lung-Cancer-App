package com.lukic

import android.app.Application
import com.lukic.data.di.networkModule
import com.lukic.di.appModule
import com.lukic.lungcancerapp.BuildConfig
import com.lukic.presentation.screen.intro.di.introModule
import com.lukic.presentation.screen.results.di.resultModule
import com.lukic.presentation.screen.userdetails.di.userInputModule
import org.koin.core.context.startKoin

class LungCancerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    appModule(this@LungCancerApplication),
                    networkModule(BuildConfig.BASE_URL, BuildConfig.API_KEY),
                    introModule,
                    userInputModule,
                    resultModule
                )
            )
        }
    }
}
