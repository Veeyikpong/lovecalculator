package com.veeyikpong.lovecalculator.ui

class MainContract {
    interface View{
        fun showLoading()
        fun hideLoading()
        fun showResult(percentage: String, description: String)
        fun showError(errorMessage: String)

        fun showFirstPersonNameEmpty()
        fun showSecondPersonNameEmpty()
    }
    interface Presenter{
        fun performLoveCalculation(firstPersonName: String, secondPersonName: String)
    }
}