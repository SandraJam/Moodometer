package com.example.octo_sdu.moodometer.listMood.presenter

import com.example.octo_sdu.moodometer.R
import com.example.octo_sdu.moodometer.listMood.view.ListViewValidate
import com.example.octo_sdu.moodometer.listMood.view.MoodViewModel
import proto.moodometer.Mood
import java.text.DateFormat
import java.util.*

class ListPresenterImpl(val listViewValidate: ListViewValidate) : ListPresenter {
    override fun success(listMood: List<Mood>) =
            listViewValidate.success(
                    listMood.map {
                        x -> MoodViewModel(
                            x.mind,
                            x.score.toString(),
                            DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault()).format(x.date),
                            when {
                                x.score >= 4 -> R.drawable.circlegood
                                x.score <= 2 -> R.drawable.circlebad
                                else -> R.drawable.circleaverage
                            })
                    }
            )

    override fun fail() = listViewValidate.fail()
}