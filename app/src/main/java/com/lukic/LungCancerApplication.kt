package com.lukic

import android.app.Application
import com.lukic.data.di.networkModule
import org.koin.core.context.startKoin

class LungCancerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    networkModule
                )
            )
        }
    }
}
