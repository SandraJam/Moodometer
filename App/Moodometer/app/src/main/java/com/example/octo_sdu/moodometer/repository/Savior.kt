package com.example.octo_sdu.moodometer.repository

import proto.moodometer.Mood
import proto.moodometer.Moods

interface Savior {
    fun saveMood(mood: Mood): Boolean

    fun getMoods(): List<Mood>

    fun getMoodsByTime(l: Long): List<Mood>

    fun gifUrl(avg: Float): String

    fun createFile()

}
