package com.bsas.androiddevs.manejoerrores.common.exception;

import com.crashlytics.android.Crashlytics;

public class ExceptionLogger {

    private static ExceptionLogger _selfInstance;

    private ExceptionLogger() {
    }

    public static void error(Throwable throwable) {
        _selfInstance.logError(throwable);
    }

    private void logError(Throwable throwable) {
        throwable.printStackTrace();
        Crashlytics.logException(throwable);
    }
}
