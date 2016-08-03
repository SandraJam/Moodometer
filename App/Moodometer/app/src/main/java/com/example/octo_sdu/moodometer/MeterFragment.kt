package com.example.octo_sdu.moodometer

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.octo_sdu.moodometer.meterMood.interactor.MeterInteractorDecorated
import com.example.octo_sdu.moodometer.meterMood.interactor.MeterInteractorImpl
import com.example.octo_sdu.moodometer.meterMood.presenter.MeterPresenterImpl
import com.example.octo_sdu.moodometer.meterMood.view.MeterViewValidate
import com.example.octo_sdu.moodometer.meterMood.view.MeterViewValidateDecorated
import com.example.octo_sdu.moodometer.repository.Manager
import com.example.octo_sdu.moodometer.repository.SaviorLocalFileImpl

class MeterFragment: Fragment(), MeterViewValidate {

    var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_main_meter, container, false)

        MeterInteractorDecorated(MeterInteractorImpl(MeterPresenterImpl(MeterViewValidateDecorated(this)), SaviorLocalFileImpl(rootView!!.context), Manager())).avgMoods(7)

        return rootView
    }

    companion object {
        fun newInstance(): MeterFragment {
            val fragment = MeterFragment()
            return fragment
        }
    }

    override fun success(avg: String, gifSentence: Int, gifURL: String, color: Int) {
        val avgTxt = rootView!!.findViewById(R.id.textView_score_avg) as TextView
        avgTxt.text = avg
        val gifTxt = rootView!!.findViewById(R.id.textView_gif) as TextView
        gifTxt.text = getString(gifSentence)
        val gifImage = rootView!!.findViewById(R.id.imageview_gif) as ImageView
        Glide.with(this).load(gifURL).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(gifImage)

        gifImage.setOnClickListener {
            MeterInteractorDecorated(MeterInteractorImpl(MeterPresenterImpl(MeterViewValidateDecorated(this)), SaviorLocalFileImpl(rootView!!.context), Manager())).avgMoods(7)
        }

        val linear = rootView!!.findViewById(R.id.linearViewMeter) as LinearLayout
        linear.setBackgroundResource(color)
    }

    override fun fail() {
    }
}