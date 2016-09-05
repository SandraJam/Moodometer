package com.example.octo_sdu.moodometer.meterMood.view

interface MeterViewValidate {
    fun success (meterViewModel: MeterViewModel)
    fun displayGif(gifUrl: String)
    fun fail()
    fun firstStart()
}