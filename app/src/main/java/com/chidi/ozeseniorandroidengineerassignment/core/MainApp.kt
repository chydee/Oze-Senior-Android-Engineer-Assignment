package com.chidi.ozeseniorandroidengineerassignment.core

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp : Application() {
    var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}