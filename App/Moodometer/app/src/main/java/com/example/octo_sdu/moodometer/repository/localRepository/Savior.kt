package com.example.octo_sdu.moodometer.repository.localRepository

import proto.moodometer.Mood
import proto.moodometer.Moods

interface Savior {
    fun saveMood(mood: Mood): Boolean

    fun getMoods(): List<Mood>

}
