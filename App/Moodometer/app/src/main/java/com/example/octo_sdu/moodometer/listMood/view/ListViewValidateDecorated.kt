package com.example.octo_sdu.moodometer.listMood.view

import android.os.Handler
import android.os.Looper
import com.example.octo_sdu.moodometer.listMood.view.ListViewValidate

class ListViewValidateDecorated(val listViewValidate: ListViewValidate) : ListViewValidate {
    override fun success(listMind: List<String>, listScore: List<String>, listDate: List<String>, whatColor: List<Int>) {
        Handler(Looper.getMainLooper()).post { listViewValidate.success(listMind, listScore, listDate, whatColor) }
    }

    override fun fail() {
        Handler(Looper.getMainLooper()).post { listViewValidate.fail() }
    }
}