package com.example.octo_sdu.moodometer

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.example.octo_sdu.moodometer.addMood.interactor.MoodInteractorDecorated
import com.example.octo_sdu.moodometer.addMood.interactor.MoodInteractorImpl
import com.example.octo_sdu.moodometer.addMood.presenter.MoodPresenterImpl
import com.example.octo_sdu.moodometer.addMood.view.MoodViewValidate
import com.example.octo_sdu.moodometer.addMood.view.MoodViewValidateDecorated
import com.example.octo_sdu.moodometer.repository.Manager
import com.example.octo_sdu.moodometer.repository.SaviorLocalFileImpl
import java.text.DateFormat
import java.util.*

class MoodActivity : AppCompatActivity(), MoodViewValidate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood)

        val textDate = findViewById(R.id.text_date) as TextView
        textDate.text = DateFormat.getDateInstance(DateFormat.DEFAULT, java.util.Locale.getDefault()).format(Date().time)

        val interactor = MoodInteractorDecorated(MoodInteractorImpl(MoodPresenterImpl(MoodViewValidateDecorated(this)), SaviorLocalFileImpl(this), Manager()))
        val fabAddMood = findViewById(R.id.fab_add_mood) as FloatingActionButton
        fabAddMood.setOnClickListener {
            interactor.addMood(
                    (findViewById(R.id.ratingBar) as RatingBar).rating,
                    (findViewById(R.id.edit_text_add_mood) as EditText).text.toString(),
                    Date().time)
        }
    }

    override fun success() {
        onBackPressed()
    }

    override fun fail() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
