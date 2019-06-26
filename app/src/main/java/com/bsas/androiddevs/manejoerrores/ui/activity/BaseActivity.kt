package com.bsas.androiddevs.manejoerrores.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bsas.androiddevs.manejoerrores.viewmodel.BaseViewModel

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewModel = this.createViewModel()
        this.bindObserversToWarningsAndErrors()
    }

    private fun bindObserversToWarningsAndErrors() {
        this.viewModel.warningMessage.observe(this, Observer { message: String? ->
            this.displayWarning(message)
            this.viewModel.errorDisplayed()
        })

        this.viewModel.errorMessage.observe(this, Observer { message: String? ->
            this.displayError(message)
            this.viewModel.errorDisplayed()
        })
    }

    private fun displayWarning(message: String?) {
        message?.let {
            Toast.makeText(this, "Warning", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayError(message: String?) {
        message?.let {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }
    }

    abstract fun createViewModel(): T


}