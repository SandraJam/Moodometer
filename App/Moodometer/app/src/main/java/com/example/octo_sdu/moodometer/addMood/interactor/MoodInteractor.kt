package com.example.octo_sdu.moodometer.addMood.interactor

interface MoodInteractor {
    fun addMood(score: Float, mind: String, date: Long)
}