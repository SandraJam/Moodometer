package com.example.octo_sdu.moodometer.meterMood.interactor

class MeterInteractorDecorated(val meterInteractor: MeterInteractor) : MeterInteractor {
    override fun findGif() =
            Thread(Runnable {
                meterInteractor.findGif()
            }).start()

    override fun avgMoods(days: Int) =
            Thread(Runnable {
                meterInteractor.avgMoods(days)
            }).start()
}