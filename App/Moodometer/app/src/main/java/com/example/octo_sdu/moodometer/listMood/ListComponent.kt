package com.example.octo_sdu.moodometer.listMood

import com.example.octo_sdu.moodometer.ListFragment
import com.example.octo_sdu.moodometer.main.MainComponent
import dagger.Component

@ListScope @Component(dependencies = arrayOf(MainComponent::class), modules = arrayOf(ListModule::class))
interface ListComponent {
    fun inject(listFragment: ListFragment)
}