package com.bsas.androiddevs.manejoerrores.presenter;

import com.bsas.androiddevs.manejoerrores.common.logging.ExceptionLogger;

import java.util.HashMap;
import java.util.Map;

public class PresenterProvider {

    private static PresenterProvider _selfInstance;
    private Map<String, Object> presenterCache = new HashMap<>();

    private PresenterProvider() {
    }

    public static PresenterProvider get() {
        if (_selfInstance == null) {
            _selfInstance = new PresenterProvider();
        }
        return _selfInstance;
    }

    @SuppressWarnings("unchecked")
    public <T> T getPresenter(Class<T> clazz) {
        if (this.presenterCache.containsKey(clazz.getName())) {
            return (T) this.presenterCache.get(clazz.getName());
        } else {
            return this.createNewPresenter(clazz);
        }
    }

    private <T> T createNewPresenter(Class<T> clazz) {
        try {
            T presenter = clazz.newInstance();
            this.presenterCache.put(clazz.getName(), presenter);
            return presenter;
        } catch (InstantiationException | IllegalAccessException e) {
            ExceptionLogger.error(e);
        }
        return null;
    }

}
