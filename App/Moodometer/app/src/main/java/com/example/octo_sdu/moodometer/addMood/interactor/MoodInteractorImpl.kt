package com.example.octo_sdu.moodometer.addMood.interactor

import com.example.octo_sdu.moodometer.addMood.presenter.MoodPresenter
import com.example.octo_sdu.moodometer.repository.Manager
import com.example.octo_sdu.moodometer.repository.Savior

class MoodInteractorImpl(val moodPresenter: MoodPresenter, val savior: Savior, val manager: Manager) : MoodInteractor {
    override fun addMood(score: Float, mind: String, date: Long) =
            if (savior.saveMood(manager.createMood(score, mind, date)))
                moodPresenter.success()
            else
                moodPresenter.fail()
}