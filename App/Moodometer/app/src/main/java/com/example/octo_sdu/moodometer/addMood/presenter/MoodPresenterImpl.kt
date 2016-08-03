package com.example.octo_sdu.moodometer.addMood.presenter

import com.example.octo_sdu.moodometer.addMood.view.MoodViewValidate

class MoodPresenterImpl(val moodViewValidate: MoodViewValidate) : MoodPresenter {
    override fun success() = moodViewValidate.success()

    override fun fail() = moodViewValidate.fail()
}