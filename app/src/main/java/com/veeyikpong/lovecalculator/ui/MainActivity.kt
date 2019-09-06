package com.veeyikpong.lovecalculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import com.veeyikpong.lovecalculator.R
import com.veeyikpong.lovecalculator.common.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(),
    MainContract.View {

    //Inject presenter
    private val mPresenter: MainPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Clear the error when there's user input
        et_personOne.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                et_personOne.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        et_personTwo.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                et_personTwo.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        et_personTwo.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                btn_calculate.performClick()

                true
            }

            false
        }

        btn_calculate.setOnClickListener {
            //You can use presenter here
            mPresenter.performLoveCalculation(
                et_personOne.text.toString(),
                et_personTwo.text.toString()
            )
        }
    }

    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
        ll_result.visibility = View.GONE
        tv_error.visibility = View.GONE
        hideKeyboard()
    }

    override fun hideLoading() {
        progressbar.visibility = View.GONE
    }

    override fun showResult(percentage: String, description: String) {
        tv_percentage.text = "$percentage%"
        tv_description.text = description
        ll_result.visibility = View.VISIBLE
    }

    override fun showFirstPersonNameEmpty() {
        til_firstPersonName.error = getString(R.string.err_name_empty)
    }

    override fun showSecondPersonNameEmpty() {
        til_secondPersonName.error = getString(R.string.err_name_empty)
    }

    override fun showError(errorMessage: String) {
        tv_error.text = errorMessage
        tv_error.visibility = View.VISIBLE
    }
}
