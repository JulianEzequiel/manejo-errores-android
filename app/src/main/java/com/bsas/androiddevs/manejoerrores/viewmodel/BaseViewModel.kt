package com.bsas.androiddevs.manejoerrores.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var warningMessage = MutableLiveData<Int?>()

    var errorMessage = MutableLiveData<Int?>()

    protected fun displayWarning(messageResource: Int) {
        warningMessage.value = messageResource
    }

    protected fun displayError(messageResource: Int) {
        errorMessage.value = messageResource
    }

    fun warningDisplayed() {
        warningMessage.value = null
    }

    fun errorDisplayed() {
        errorMessage.value = null
    }

}

