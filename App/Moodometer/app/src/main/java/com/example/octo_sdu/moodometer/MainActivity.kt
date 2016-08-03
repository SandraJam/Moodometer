package com.example.octo_sdu.moodometer

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.octo_sdu.moodometer.meterMood.interactor.MeterInteractorDecorated
import com.example.octo_sdu.moodometer.meterMood.interactor.MeterInteractorImpl
import com.example.octo_sdu.moodometer.meterMood.presenter.MeterPresenterImpl
import com.example.octo_sdu.moodometer.meterMood.view.MeterViewValidateDecorated
import com.example.octo_sdu.moodometer.repository.Manager
import com.example.octo_sdu.moodometer.repository.SaviorLocalFileImpl

class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val savior = SaviorLocalFileImpl(this)
        savior.createFile()

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        mViewPager = findViewById(R.id.container) as ViewPager
        mViewPager!!.adapter = mSectionsPagerAdapter

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            startActivity(Intent(this, MoodActivity::class.java))
            finish()
        }
    }
}
