package com.example.octo_sdu.moodometer.meterMood.presenter

import com.example.octo_sdu.moodometer.R
import com.example.octo_sdu.moodometer.meterMood.view.MeterViewModel
import com.example.octo_sdu.moodometer.meterMood.view.MeterViewValidate

class MeterPresenterImpl(val meterViewValidate: MeterViewValidate) : MeterPresenter {

    override fun firstStart() {
        meterViewValidate.firstStart()
    }

    override fun displayGif(gifUrl: String) {
        meterViewValidate.displayGif(gifUrl)
    }

    override fun success(avg: Float) =
        meterViewValidate.success(
                MeterViewModel(avg.toString(),
                        when {
                            avg > 4.5 -> R.string.good_mind
                            avg > 3.5 -> R.string.pretty_good_mind
                            avg > 2 -> R.string.average_mind
                            else -> R.string.bad_mind
                        },
                        when {
                            avg >= 4 -> R.drawable.circlebiggood
                            avg <= 2 -> R.drawable.circlebigbad
                            else -> R.drawable.circlebigaverage
                        },
                        when {
                            avg >= 4 -> R.color.colorGood
                            avg <= 2 -> R.color.colorBad
                            else -> R.color.colorAverage
                        }
                        ))

    override fun fail() = meterViewValidate.fail()
}