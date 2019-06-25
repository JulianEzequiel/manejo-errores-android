package com.bsas.androiddevs.manejoerrores.ui.view;

public interface BaseView {

    void displayWarningMessage(String message);

    void displayWarningMessage(int stringId);

    void displayErrorMessage(int stringId);

    void displayErrorMessage(String message);

}
