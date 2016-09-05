package com.example.octo_sdu.moodometer.listMood.presenter

import proto.moodometer.Mood

interface ListPresenter {
    fun fail()
    fun success(listMood: List<Mood>)
}