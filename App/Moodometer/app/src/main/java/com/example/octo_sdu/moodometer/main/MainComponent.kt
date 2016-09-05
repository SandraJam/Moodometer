package com.example.octo_sdu.moodometer.main

import com.example.octo_sdu.moodometer.repository.localRepository.Savior
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(MainModule::class)) interface MainComponent {
    fun savior(): Savior
}