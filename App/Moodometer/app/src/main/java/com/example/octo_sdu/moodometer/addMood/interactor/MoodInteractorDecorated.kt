package com.example.octo_sdu.moodometer.addMood.interactor

class MoodInteractorDecorated(val moodInteractor: MoodInteractor) : MoodInteractor {
    override fun addMood(score: Float, mind: String, date: Long) =
            Thread(Runnable {
                moodInteractor.addMood(score, mind, date)
            }).start()
}