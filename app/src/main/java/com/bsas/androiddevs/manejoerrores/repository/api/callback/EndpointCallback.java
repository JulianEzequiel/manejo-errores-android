package com.bsas.androiddevs.manejoerrores.repository.api.callback;

public interface EndpointCallback<T> {

    void onSuccess(T data);

    void onFailure(Throwable t);

}
