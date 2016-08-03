package com.example.octo_sdu.moodometer.meterMood.interactor

import com.example.octo_sdu.moodometer.meterMood.presenter.MeterPresenter
import com.example.octo_sdu.moodometer.repository.Manager
import com.example.octo_sdu.moodometer.repository.Savior
import java.util.*

class MeterInteractorImpl(val meterPresenter: MeterPresenter, val savior: Savior, val manager: Manager) : MeterInteractor {
    val daytoms = 86400000

    override fun avgMoods(days: Int) {
        val avg =  manager.avgMoods(savior.getMoodsByTime(Date().time - days*daytoms))
        meterPresenter.success(
                avg,
                manager.gifSentence(avg),
                savior.gifUrl(avg),
                manager.justColor(avg)
        )
    }
}