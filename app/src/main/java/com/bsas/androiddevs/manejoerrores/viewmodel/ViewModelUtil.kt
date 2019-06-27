package com.bsas.androiddevs.manejoerrores.viewmodel

import com.bsas.androiddevs.manejoerrores.common.exception.ControlledException
import com.bsas.androiddevs.manejoerrores.common.exception.UIErrorException
import com.bsas.androiddevs.manejoerrores.common.logging.ExceptionLogger
import kotlin.reflect.KClass

inline fun BaseViewModel.launchControlled(alerts: Array<KClass<*>> = arrayOf(UIErrorException::class),
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
            ExceptionLogger.error(controlledException)
            throw controlledException
        }
    }
}