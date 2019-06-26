package com.bsas.androiddevs.manejoerrores.common.logging

import com.crashlytics.android.Crashlytics

class ExceptionLogger {

    companion object {

        private val exceptionLogger = ExceptionLogger()

        fun error(throwable: Throwable) {
            exceptionLogger.logError(throwable)
        }
    }

    fun logError(throwable: Throwable) {
        throwable.printStackTrace()
        Crashlytics.logException(throwable)
    }

}