package com.bsas.androiddevs.manejoerrores.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bsas.androiddevs.manejoerrores.ui.activity.util.displayErrorDialog
import com.bsas.androiddevs.manejoerrores.ui.activity.util.displayWarningDialog
import com.bsas.androiddevs.manejoerrores.viewmodel.BaseViewModel

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewModel = this.createViewModel()
        this.bindObserversToWarningsAndErrors()
    }

    private fun bindObserversToWarningsAndErrors() {
        this.viewModel.warningMessage.observe(this, Observer { message: Int? ->
            this.displayWarning(message)
        })

        this.viewModel.errorMessage.observe(this, Observer { message: Int? ->
            this.displayError(message)
        })
    }

    private fun displayWarning(message: Int?) {
        message?.let {
            this.displayWarningDialog(message)
            this.viewModel.warningDisplayed()
        }
    }

    private fun displayError(message: Int?) {
        message?.let {
            this.displayErrorDialog(message)
            this.viewModel.errorDisplayed()
        }
    }

    abstract fun createViewModel(): T


}