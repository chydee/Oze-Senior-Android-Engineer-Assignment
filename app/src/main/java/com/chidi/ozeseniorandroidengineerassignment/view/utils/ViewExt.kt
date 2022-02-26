package com.chidi.ozeseniorandroidengineerassignment.view.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {

    this.visibility = View.GONE
}

fun View.showSnackBar(message: String) {
    this.let {
        Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
    }
}

fun View.snackBarWithAction(message: String, actionName: String, action: () -> Unit) {
    this.let {
        Snackbar.make(it, message, Snackbar.LENGTH_LONG)
            .setAction(actionName) {
                action()
            }
            .show()
    }
}