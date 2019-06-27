package com.bsas.androiddevs.manejoerrores.common.logging.aspect

import android.util.Log
import com.bsas.androiddevs.manejoerrores.common.logging.ExceptionLogger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import java.lang.reflect.Method

@Aspect
class UIAlertAspect {

    @Pointcut("execution(@com.bsas.androiddevs.manejoerrores.logging.CheckAlerts * *(..))")
    fun checkAlertsPointcut() {
    }

    @Around(value = "checkAlertsPointcut()")
    @Throws(Throwable::class)
    fun aroundAlertPointcut(joinPoint: ProceedingJoinPoint) {
        val method = (joinPoint.signature as MethodSignature).method
        try {
            joinPoint.proceed()
        } catch (t: Throwable) {
            ExceptionLogger.error(t)
            this.handleException(method, t)
        }
    }

    @Throws(Throwable::class)
    private fun handleException(method: Method, e: Throwable) {
        val checkAlertsAnnotation = method.getAnnotation(CheckAlerts::class.java)

        if (checkAlertsAnnotation.alerts.contains(e::class)) {
            //TODO
            Log.d("UIAlertAspect", "Alerta para UI: " + e.message)
        } else if (checkAlertsAnnotation.errors.contains(e::class)) {
            //TODO
            Log.d("UIAlertAspect", "Error para UI: " + e.message)
        } else {
            throw e
        }
    }

}