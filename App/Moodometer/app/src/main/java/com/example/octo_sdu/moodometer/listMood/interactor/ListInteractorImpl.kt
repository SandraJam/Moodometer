package com.example.octo_sdu.moodometer.listMood.interactor

import com.example.octo_sdu.moodometer.listMood.presenter.ListPresenter
import com.example.octo_sdu.moodometer.repository.localRepository.Savior

class ListInteractorImpl(val listPresenter: ListPresenter, val savior: Savior) : ListInteractor {
    override fun getMoods() = listPresenter.success(savior.getMoods())
}