package com.veeyikpong.lovecalculator.ui

import com.veeyikpong.lovecalculator.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainPresenter(val view: MainContract.View) : MainContract.Presenter, KoinComponent {
    private val mApiService: ApiService by inject()

    override fun performLoveCalculation(firstPersonName: String, secondPersonName: String) {
        if(!validate(firstPersonName,secondPersonName))
            return

        view.showLoading()
        mApiService.calculateLovePercentage(
            xRapidApiHost = "love-calculator.p.rapidapi.com",
            xRapidApiKey = "6d099ca563msh4afb7489844c1bap10445djsnaacc81dab8bd",
            firstPersonName = firstPersonName,
            secondPersonName = secondPersonName
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                view.hideLoading()
                //Success
                if (response.percentage.isNotEmpty()) {
                    view.showResult(response.percentage, response.description)
                    return@subscribe
                }

                view.showError(response.message)
            }, { error ->
                view.hideLoading()
                view.showError(error.message!!)
            })
    }

    fun validate(firstPersonName: String, secondPersonName: String): Boolean{
        var validated = true

        if(firstPersonName.isEmpty()){
            view.showFirstPersonNameEmpty()
            validated = false
        }

        if(secondPersonName.isEmpty()){
            view.showSecondPersonNameEmpty()
            validated = false
        }

        return validated
    }
}