package com.bsas.androiddevs.manejoerrores.ui.activity.util

import androidx.appcompat.app.AppCompatActivity
import com.bsas.androiddevs.manejoerrores.ui.dialog.GenericErrorDialog
import com.bsas.androiddevs.manejoerrores.ui.dialog.GenericWarningDialog

fun AppCompatActivity.displayWarningDialog(message : Int) {
    GenericWarningDialog(message).show(this.supportFragmentManager, null)
}

fun AppCompatActivity.displayErrorDialog(message : Int) {
    GenericErrorDialog(message).show(this.supportFragmentManager, null)
}