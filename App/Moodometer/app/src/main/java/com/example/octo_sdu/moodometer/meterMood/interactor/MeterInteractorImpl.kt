package com.example.octo_sdu.moodometer.meterMood.interactor

import com.example.octo_sdu.moodometer.meterMood.presenter.MeterPresenter
import com.example.octo_sdu.moodometer.repository.gifRepository.GifRepository
import com.example.octo_sdu.moodometer.repository.localRepository.Savior
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.TimeUnit

class MeterInteractorImpl(val meterPresenter: MeterPresenter, val savior: Savior, val gifRepository: GifRepository) : MeterInteractor {
    var avg: Float = 0f

    override fun avgMoods(days: Int) {
        val l = Date().time - TimeUnit.DAYS.toMillis(days.toLong())
        val moodsByTime = savior.getMoods().filter { it.date > l }

        when (moodsByTime.size) {
            0 -> meterPresenter.firstStart()
            else -> {
                avg = BigDecimal(moodsByTime.sumByDouble { it.score.toDouble() }.div(moodsByTime.size)).setScale(1, BigDecimal.ROUND_HALF_UP).toFloat()
                meterPresenter.success(avg)
            }
        }
        findGif()
    }

    override fun findGif() =
        meterPresenter.displayGif(
                when{
                    avg == 0f -> gifRepository.gifWelcome()
                    avg < 2f -> gifRepository.gifBadTime()
                    avg < 3.5f -> gifRepository.gifAverageTime()
                    avg < 4.5f -> gifRepository.gifCoolTime()
                    else -> gifRepository.gifBossTime()
                }
        )

}