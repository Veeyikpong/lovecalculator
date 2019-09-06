package com.veeyikpong.lovecalculator.injection

import com.veeyikpong.lovecalculator.ui.MainContract
import com.veeyikpong.lovecalculator.ui.MainPresenter
import com.veeyikpong.lovecalculator.network.ApiService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single { ApiService.create(androidContext()) }
}

val presenterModule = module {
    factory { (view: MainContract.View) ->
        MainPresenter(
            view
        )
    }
}