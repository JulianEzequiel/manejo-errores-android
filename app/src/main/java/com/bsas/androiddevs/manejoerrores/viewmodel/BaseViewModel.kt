package com.bsas.androiddevs.manejoerrores.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var warningMessage = MutableLiveData<Int?>()

    var errorMessage = MutableLiveData<Int?>()

    fun warningDisplayed() {
        warningMessage.value = null
    }

    fun errorDisplayed() {
        errorMessage.value = null
    }

}

