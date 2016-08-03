package com.example.octo_sdu.moodometer.listMood.presenter

import proto.moodometer.Mood

interface ListPresenter {
    fun success(listMood: List<Mood>, whatColor: List<Int>)
    fun fail()
}