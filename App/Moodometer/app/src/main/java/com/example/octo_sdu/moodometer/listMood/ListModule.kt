package com.example.octo_sdu.moodometer.listMood

import com.example.octo_sdu.moodometer.listMood.interactor.ListInteractor
import com.example.octo_sdu.moodometer.listMood.interactor.ListInteractorDecorated
import com.example.octo_sdu.moodometer.listMood.interactor.ListInteractorImpl
import com.example.octo_sdu.moodometer.listMood.presenter.ListPresenter
import com.example.octo_sdu.moodometer.listMood.presenter.ListPresenterImpl
import com.example.octo_sdu.moodometer.listMood.view.ListViewValidate
import com.example.octo_sdu.moodometer.listMood.view.ListViewValidateDecorated
import com.example.octo_sdu.moodometer.repository.localRepository.Savior
import dagger.Module
import dagger.Provides

@Module
class ListModule() {

    @ListScope @Provides fun providesListViewValidateDecorated() = ListViewValidateDecorated()

    @Provides fun providesListPresenter(listViewValidate: ListViewValidate): ListPresenter = ListPresenterImpl(listViewValidate)

    @Provides fun providesListInteractor(listPresenter: ListPresenter, savior: Savior): ListInteractor =
            ListInteractorDecorated(
                    ListInteractorImpl(listPresenter, savior)
            )

    @Provides fun providesListViewValidate(listViewValidate: ListViewValidateDecorated): ListViewValidate = listViewValidate
}