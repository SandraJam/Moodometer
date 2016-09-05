package com.example.octo_sdu.moodometer

import android.app.Application
import com.example.octo_sdu.moodometer.main.MainDependencies

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MainDependencies.init(this)
    }
}