package com.example.octo_sdu.moodometer.listMood.interactor

import com.example.octo_sdu.moodometer.listMood.presenter.ListPresenter
import com.example.octo_sdu.moodometer.repository.Manager
import com.example.octo_sdu.moodometer.repository.Savior

class ListInteractorImpl(val listPresenter: ListPresenter, val savior: Savior) : ListInteractor {
    val manager = Manager()

    override fun getMoods() {
        val moods = savior.getMoods()
        listPresenter.success(moods, manager.whatColor(moods))
    }
}