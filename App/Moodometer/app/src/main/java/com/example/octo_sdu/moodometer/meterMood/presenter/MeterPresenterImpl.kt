package com.example.octo_sdu.moodometer.meterMood.presenter

import com.example.octo_sdu.moodometer.meterMood.view.MeterViewValidate

class MeterPresenterImpl(val meterViewValidate: MeterViewValidate) : MeterPresenter {
    override fun success(avg: Float, gifSentence: Int, gifURL: String, color: Int) =
        meterViewValidate.success(avg.toString(), gifSentence, gifURL, color)

    override fun fail() = meterViewValidate.fail()
}