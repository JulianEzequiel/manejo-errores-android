package com.bsas.androiddevs.manejoerrores.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private var _warningMessage = MutableLiveData<String?>()
    val warningMessage: LiveData<String?>
        get() = _warningMessage

    private var _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    protected fun displayWarning(message: String) {
        _warningMessage.value = message
    }

    protected fun displayError(message: String) {
        _errorMessage.value = message
    }

    fun warningDisplayed() {
        _warningMessage.value = null
    }

    fun errorDisplayed() {
        _warningMessage.value = null
    }
}