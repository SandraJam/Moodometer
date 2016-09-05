package com.example.octo_sdu.moodometer.addMood.interactor

import com.example.octo_sdu.moodometer.addMood.presenter.MoodPresenter
import com.example.octo_sdu.moodometer.repository.localRepository.Savior
import proto.moodometer.Mood

class MoodInteractorImpl(val moodPresenter: MoodPresenter, val savior: Savior) : MoodInteractor {
    override fun addMood(score: Float, mind: String, date: Long) =
            if (savior.saveMood(Mood.Builder().score(score).mind(mind).date(date).build()))
                moodPresenter.success()
            else
                moodPresenter.fail()
}