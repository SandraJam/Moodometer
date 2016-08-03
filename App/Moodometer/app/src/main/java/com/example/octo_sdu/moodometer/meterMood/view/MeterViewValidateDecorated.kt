package com.example.octo_sdu.moodometer.meterMood.view

import android.os.Handler
import android.os.Looper

class MeterViewValidateDecorated(val meterViewValidate: MeterViewValidate) : MeterViewValidate {
    override fun success(avg: String, gifSentence: Int, gifURL: String, color: Int) {
        Handler(Looper.getMainLooper()).post { meterViewValidate.success(avg, gifSentence, gifURL, color) }
    }

    override fun fail() {
        Handler(Looper.getMainLooper()).post { meterViewValidate.fail() }
    }
}