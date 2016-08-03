package com.example.octo_sdu.moodometer.meterMood.presenter

interface MeterPresenter {
    fun success(avg: Float, gifSentence: Int, gifURL: String, color: Int)
    fun fail()
}