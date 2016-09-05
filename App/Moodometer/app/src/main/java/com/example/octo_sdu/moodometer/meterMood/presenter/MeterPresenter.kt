package com.example.octo_sdu.moodometer.meterMood.presenter

interface MeterPresenter {
    fun success(avg: Float)
    fun firstStart()
    fun fail()
    fun displayGif(gifUrl: String)
}