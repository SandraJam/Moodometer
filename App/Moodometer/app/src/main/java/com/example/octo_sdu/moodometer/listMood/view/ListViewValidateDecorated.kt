package com.example.octo_sdu.moodometer.listMood.view

import android.os.Handler
import android.os.Looper

class ListViewValidateDecorated() : ListViewValidate {

    var listViewValidate: ListViewValidate? = null

    override fun success(listMoodViewModel: List<MoodViewModel>) {
        Handler(Looper.getMainLooper()).post { listViewValidate?.success(listMoodViewModel) }
    }

    override fun fail() {
        Handler(Looper.getMainLooper()).post { listViewValidate?.fail() }
    }
}