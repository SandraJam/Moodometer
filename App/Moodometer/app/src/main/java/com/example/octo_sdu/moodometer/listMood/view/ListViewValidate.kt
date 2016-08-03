package com.example.octo_sdu.moodometer.listMood.view

interface ListViewValidate {
    fun success(listMind: List<String>, listScore: List<String>, listDate: List<String>, whatColor: List<Int>)
    fun fail()
}