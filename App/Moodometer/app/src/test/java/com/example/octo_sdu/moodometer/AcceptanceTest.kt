package com.example.octo_sdu.moodometer

import com.example.octo_sdu.moodometer.addMood.interactor.MoodInteractorImpl
import com.example.octo_sdu.moodometer.addMood.presenter.MoodPresenterImpl
import com.example.octo_sdu.moodometer.addMood.view.MoodViewValidate
import com.example.octo_sdu.moodometer.listMood.interactor.ListInteractorImpl
import com.example.octo_sdu.moodometer.listMood.presenter.ListPresenterImpl
import com.example.octo_sdu.moodometer.listMood.view.ListViewValidate
import com.example.octo_sdu.moodometer.listMood.view.MoodViewModel
import com.example.octo_sdu.moodometer.meterMood.interactor.MeterInteractorImpl
import com.example.octo_sdu.moodometer.meterMood.presenter.MeterPresenterImpl
import com.example.octo_sdu.moodometer.meterMood.view.MeterViewModel
import com.example.octo_sdu.moodometer.meterMood.view.MeterViewValidate
import com.example.octo_sdu.moodometer.repository.gifRepository.GifRepository
import com.example.octo_sdu.moodometer.repository.localRepository.SaviorLocalFileImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.io.File
import java.util.*

class AcceptanceTest() {

    @Test
    fun testListMood() {
        Locale.setDefault(Locale.ENGLISH)
        val viewValidate = object : ListViewValidate{
            override fun success(listMoodViewModel: List<MoodViewModel>) {
                assertThat(listMoodViewModel).hasSize(3)

                assertThat(listMoodViewModel)
                        .containsExactly(
                                MoodViewModel("This is text", "1.0", "Aug 24, 2016", R.drawable.circlebad),
                                MoodViewModel("", "5.0", "Aug 24, 2016", R.drawable.circlegood),
                                MoodViewModel("", "3.5", "Aug 24, 2016", R.drawable.circleaverage)
                        )
            }

            override fun fail() {
                throw Exception("Should not fail")
            }

        }
        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource("moodTest")!!.file)
        val interactor = ListInteractorImpl(ListPresenterImpl(viewValidate),
                SaviorLocalFileImpl(file))
        interactor.getMoods()
    }

    @Test
    fun testMeterMood() {
        val viewValidate = object : MeterViewValidate {
            override fun success(meterViewModel: MeterViewModel) {
                assertThat(meterViewModel).isEqualTo(MeterViewModel("3.2", R.string.average_mind, R.drawable.circlebigaverage, R.color.colorAverage))
            }

            override fun fail() {
                throw Exception("Should not fail")
            }

            override fun displayGif(gifUrl: String) {
                assertThat(gifUrl).contains(".giphy.com/media/")
            }

            override fun firstStart() {
                throw Exception("Not first Start")
            }

        }

        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource("moodTest")!!.file)
        val interactor = MeterInteractorImpl(MeterPresenterImpl(viewValidate), SaviorLocalFileImpl(file), GifRepository())
        interactor.avgMoods(7)
    }

    @Test
    fun testAddMood() {
        Locale.setDefault(Locale.ENGLISH)
        val viewValidateList = object : ListViewValidate{
            override fun success(listMoodViewModel: List<MoodViewModel>) {
                assertThat(listMoodViewModel)
                        .isEmpty()
            }

            override fun fail() {
                throw Exception("Should not fail")
            }

        }
        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource("moodTestAdd")!!.file)
        val interactorList = ListInteractorImpl(ListPresenterImpl(viewValidateList),
                SaviorLocalFileImpl(file))
        interactorList.getMoods()

        val viewValidate = object: MoodViewValidate {
            val list = mutableListOf<String>()

            override fun success() {
                list.add("success")
            }

            override fun fail() {
                list.add("fail")
            }
        }

        val interactor = MoodInteractorImpl(MoodPresenterImpl(viewValidate), SaviorLocalFileImpl(file))
        interactor.addMood(3.0f, "", Date().time)
        assertThat(viewValidate.list).containsOnly("success")

        val viewValidateListAfter = object : ListViewValidate{
            override fun success(listMoodViewModel: List<MoodViewModel>) {
                assertThat(listMoodViewModel).containsExactly(
                        MoodViewModel("", "3.0", "Aug 24, 2016", R.drawable.circleaverage)
                )
            }

            override fun fail() {
                throw Exception("Should not fail")
            }

        }

        val interactorListAfter = ListInteractorImpl(ListPresenterImpl(viewValidateListAfter),
                SaviorLocalFileImpl(file))
        interactorListAfter.getMoods()
    }
}