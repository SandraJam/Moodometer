package com.example.octo_sdu.moodometer.repository

import com.example.octo_sdu.moodometer.R
import proto.moodometer.Mood
import java.math.BigDecimal

class Manager() {


    fun createMood(score: Float, mind: String, date: Long): Mood =
            Mood.Builder().score(score).mind(mind).date(date).build()

    fun avgMoods(moodsByTime: List<Mood>): Float =
            when (moodsByTime.size) {
                0 -> 0.0f
                else -> BigDecimal(moodsByTime.sumByDouble { it.score.toDouble() }.div(moodsByTime.size)).setScale(1, BigDecimal.ROUND_HALF_UP).toFloat()
            }

    fun gifSentence(note: Float) =
            when {
                note > 4.5 -> R.string.good_mind
                note > 3.5 -> R.string.pretty_good_mind
                note > 2 -> R.string.average_mind
                else -> R.string.bad_mind
            }

    fun whatColor(moods: List<Mood>): List<Int> =
            moods.map {
                justColor(it.score)
            }

    fun justColor(avg: Float): Int =
         when {
            avg > 4 -> R.color.colorGood
            avg < 2 -> R.color.colorBad
            else -> R.color.colorAverage
        }
}
