package com.bsas.androiddevs.manejoerrores.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private var _warningMessage = MutableLiveData<Int?>()
    val warningMessage: LiveData<Int?>
        get() = _warningMessage

    private var _errorMessage = MutableLiveData<Int?>()
    val errorMessage: LiveData<Int?>
        get() = _errorMessage

    protected fun displayWarning(messageResource: Int) {
        _warningMessage.value = messageResource
    }

    protected fun displayError(messageResource: Int) {
        _errorMessage.value = messageResource
    }

    fun warningDisplayed() {
        _warningMessage.value = null
    }

    fun errorDisplayed() {
        _warningMessage.value = null
    }
}