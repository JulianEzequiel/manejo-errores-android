package com.bsas.androiddevs.manejoerrores.common.logging.aspect

import com.bsas.androiddevs.manejoerrores.common.exception.UIAlertException
import com.bsas.androiddevs.manejoerrores.common.exception.UIErrorException
import kotlin.reflect.KClass

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class CheckAlerts(
        val alerts: Array<KClass<*>> = arrayOf(UIAlertException::class),
        val errors: Array<KClass<*>> = arrayOf(UIErrorException::class)
)