package com.veeyikpong.lovecalculator

import android.app.Application
import com.veeyikpong.lovecalculator.injection.networkModule
import com.veeyikpong.lovecalculator.injection.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(
                networkModule,
                presenterModule
            ))
            androidContext(this@MyApplication)
        }
    }
}