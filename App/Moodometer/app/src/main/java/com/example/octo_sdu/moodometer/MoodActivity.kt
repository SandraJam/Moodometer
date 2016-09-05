package com.example.octo_sdu.moodometer

import android.app.Activity
import android.os.Bundle
import android.widget.*
import com.example.octo_sdu.moodometer.addMood.interactor.MoodInteractorDecorated
import com.example.octo_sdu.moodometer.addMood.interactor.MoodInteractorImpl
import com.example.octo_sdu.moodometer.addMood.presenter.MoodPresenterImpl
import com.example.octo_sdu.moodometer.addMood.view.MoodViewValidate
import com.example.octo_sdu.moodometer.addMood.view.MoodViewValidateDecorated
import com.example.octo_sdu.moodometer.repository.localRepository.SaviorLocalFileImpl
import java.io.File
import java.text.DateFormat
import java.util.*

class MoodActivity : Activity(), MoodViewValidate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood)
        setTheme(android.R.style.Theme_Material_Light_Dialog)

        val textDate = findViewById(R.id.text_date) as TextView
        textDate.text = DateFormat.getDateInstance(DateFormat.DEFAULT, java.util.Locale.getDefault()).format(Date().time)

        val interactor = MoodInteractorDecorated(MoodInteractorImpl(MoodPresenterImpl(MoodViewValidateDecorated(this)), SaviorLocalFileImpl(File(applicationContext.filesDir.path.toString() +"/mood"))))
        val buttonAddMood = findViewById(R.id.button_add_mood) as Button
        buttonAddMood.setOnClickListener {
            interactor.addMood(
                    (findViewById(R.id.ratingBar) as RatingBar).rating,
                    (findViewById(R.id.edit_text_add_mood) as EditText).text.toString(),
                    Date().time)
        }
    }

    override fun success() {
        finish()
    }

    override fun fail() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }
}
