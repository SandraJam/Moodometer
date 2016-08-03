package com.example.octo_sdu.moodometer.meterMood.interactor

class MeterInteractorDecorated(val meterInteractor: MeterInteractor) : MeterInteractor {
    override fun avgMoods(days: Int) =
            Thread(Runnable {
                meterInteractor.avgMoods(days)
            }).start()
}