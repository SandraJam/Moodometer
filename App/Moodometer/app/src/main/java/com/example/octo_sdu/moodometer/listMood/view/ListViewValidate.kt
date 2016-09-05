package com.example.octo_sdu.moodometer.listMood.view

interface ListViewValidate {
    fun success(listMoodViewModel: List<MoodViewModel>)
    fun fail()
}