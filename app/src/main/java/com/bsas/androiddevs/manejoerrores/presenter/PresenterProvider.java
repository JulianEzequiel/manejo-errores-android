package com.bsas.androiddevs.manejoerrores.presenter;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;
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
    public <T> T getPresenter(Context context, Class<T> clazz) {
        if (this.presenterCache.containsKey(clazz.getName())) {
            return (T) this.presenterCache.get(clazz.getName());
        } else {
            return this.createNewPresenter(context, clazz);
        }
    }

    private <T> T createNewPresenter(Context context, Class<T> clazz) {
        try {
            T presenter = clazz.getConstructor(Context.class).newInstance(context);
            this.presenterCache.put(clazz.getName(), presenter);
            return presenter;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
