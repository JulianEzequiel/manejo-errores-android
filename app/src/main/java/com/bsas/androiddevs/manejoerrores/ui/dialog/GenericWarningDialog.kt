package com.bsas.androiddevs.manejoerrores.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bsas.androiddevs.manejoerrores.R

class GenericWarningDialog(val messageResource: Int) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = this.activity!!

        val view = activity.layoutInflater.inflate(R.layout.generic_warning_dialog, null)

        view.findViewById<TextView>(R.id.alert_message_text).text = activity.getText(this.messageResource)

        val alert = AlertDialog.Builder(activity)
        alert.setView(view)

        return alert.create()
    }


}