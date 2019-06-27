package com.bsas.androiddevs.manejoerrores.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.bsas.androiddevs.manejoerrores.R;

public class GenericWarningDialog extends DialogFragment {

    private int messageResource;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        FragmentActivity fragmentActivity = this.getActivity();

        View view = fragmentActivity.getLayoutInflater().inflate(R.layout.generic_warning_dialog, null);

        ((TextView) view.findViewById(R.id.alert_message_text)).setText(fragmentActivity.getText(this.messageResource));

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(fragmentActivity);
        alertDialogBuilder.setView(view);

        return alertDialogBuilder.create();
    }

    public void setMessageResource(int messageResource) {
        this.messageResource = messageResource;
    }
}

