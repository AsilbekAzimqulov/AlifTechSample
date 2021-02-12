package com.example.aliftechexample.ui

import android.app.Application
import com.example.aliftechexample.data.core.LocalDatabase

class App : Application() {

    override fun onCreate() {
        LocalDatabase.init(this)
        super.onCreate()
    }
}