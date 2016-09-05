package com.example.octo_sdu.moodometer.main

import android.content.Context
import com.example.octo_sdu.moodometer.repository.localRepository.Savior
import com.example.octo_sdu.moodometer.repository.localRepository.SaviorLocalFileImpl
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module class MainModule(val context: Context) {

    @Singleton @Provides fun providesSavior(): Savior =
            SaviorLocalFileImpl(
                    File(context.filesDir.path.toString()+"/mood")
            )
}
