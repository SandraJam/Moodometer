package com.example.octo_sdu.moodometer.listMood.presenter

import com.example.octo_sdu.moodometer.listMood.view.ListViewValidate
import proto.moodometer.Mood
import java.text.DateFormat
import java.util.*

class ListPresenterImpl(val listViewValidate: ListViewValidate) : ListPresenter {
    override fun success(listMood: List<Mood>, whatColor: List<Int>) =
            listViewValidate.success(
                listMood.map { it.mind },
                listMood.map { it.score.toString() },
                listMood.map { DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault()).format(it.date) }, whatColor)


    override fun fail() = listViewValidate.fail()
}