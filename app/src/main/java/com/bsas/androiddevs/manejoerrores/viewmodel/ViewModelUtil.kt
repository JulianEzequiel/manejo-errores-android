package com.bsas.androiddevs.manejoerrores.viewmodel

import com.bsas.androiddevs.manejoerrores.common.exception.ControlledException
import com.bsas.androiddevs.manejoerrores.common.exception.UIAlertException
import com.bsas.androiddevs.manejoerrores.common.exception.UIErrorException
import kotlin.reflect.KClass

//TODO : ver si directamente se puede hacer con ViewModel:
// - Las dos funciones estas (extension function)
// - El warningMesage y el errorMessage (extension property???)

inline fun BaseViewModel.launchControlled(alerts: Array<KClass<*>> = arrayOf(UIAlertException::class),
                                          errors: Array<KClass<*>> = arrayOf(UIErrorException::class),
                                          block: BaseViewModel.() -> Unit): BaseViewModel {
    try {
        block(this)
    } catch (t: ControlledException) {
        this.handleControlledException(alerts, errors, t)
    }
    return this
}

fun BaseViewModel.handleControlledException(alerts: Array<KClass<*>>, errors: Array<KClass<*>>, controlledException: ControlledException) {
    when {
        alerts.contains(controlledException::class) -> this.warningMessage.value = controlledException.reasonStringResource
        errors.contains(controlledException::class) -> this.errorMessage.value = controlledException.reasonStringResource
        else -> {
            throw controlledException
        }
    }
}