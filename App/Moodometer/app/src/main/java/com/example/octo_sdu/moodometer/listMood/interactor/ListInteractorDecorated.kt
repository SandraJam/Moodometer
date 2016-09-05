package com.example.octo_sdu.moodometer.listMood.interactor

class ListInteractorDecorated(val listInteractor: ListInteractor) : ListInteractor {
    override fun getMoods() =
            Thread(Runnable {
                listInteractor.getMoods()
            }).start()
}