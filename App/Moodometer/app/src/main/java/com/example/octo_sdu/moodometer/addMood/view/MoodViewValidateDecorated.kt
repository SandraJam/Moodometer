package com.example.octo_sdu.moodometer.addMood.view

import android.os.Handler
import android.os.Looper

class MoodViewValidateDecorated(val moodViewValidate: MoodViewValidate) : MoodViewValidate {
    override fun success() {
        Handler(Looper.getMainLooper()).post { moodViewValidate.success() }
    }

    override fun fail() {
        Handler(Looper.getMainLooper()).post { moodViewValidate.fail() }
    }
}