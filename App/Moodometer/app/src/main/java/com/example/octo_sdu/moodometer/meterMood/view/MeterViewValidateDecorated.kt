package com.example.octo_sdu.moodometer.meterMood.view

import android.os.Handler
import android.os.Looper
import com.example.octo_sdu.moodometer.meterMood.view.MeterViewModel

class MeterViewValidateDecorated(val meterViewValidate: MeterViewValidate) : MeterViewValidate {
    override fun firstStart() {
        Handler(Looper.getMainLooper()).post { meterViewValidate.firstStart() }
    }

    override fun displayGif(gifUrl: String) {
        Handler(Looper.getMainLooper()).post { meterViewValidate.displayGif(gifUrl) }
    }

    override fun success(meterViewModel: MeterViewModel) {
        Handler(Looper.getMainLooper()).post { meterViewValidate.success(meterViewModel) }
    }

    override fun fail() {
        Handler(Looper.getMainLooper()).post { meterViewValidate.fail() }
    }
}