package com.example.octo_sdu.moodometer.meterMood.view

interface MeterViewValidate {
    fun success (avg: String, gifSentence: Int, gifURL: String, color: Int)
    fun fail()
}