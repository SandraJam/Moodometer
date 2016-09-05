package com.example.octo_sdu.moodometer

import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.octo_sdu.moodometer.meterMood.interactor.MeterInteractor
import com.example.octo_sdu.moodometer.meterMood.interactor.MeterInteractorDecorated
import com.example.octo_sdu.moodometer.meterMood.interactor.MeterInteractorImpl
import com.example.octo_sdu.moodometer.meterMood.presenter.MeterPresenterImpl
import com.example.octo_sdu.moodometer.meterMood.view.MeterViewModel
import com.example.octo_sdu.moodometer.meterMood.view.MeterViewValidate
import com.example.octo_sdu.moodometer.meterMood.view.MeterViewValidateDecorated
import com.example.octo_sdu.moodometer.repository.gifRepository.GifRepository
import com.example.octo_sdu.moodometer.repository.localRepository.SaviorLocalFileImpl
import java.io.File

class MeterFragment: Fragment(), MeterViewValidate {
    lateinit var rootView: View
    lateinit var interactor: MeterInteractor

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_main_meter, container, false)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        interactor = MeterInteractorDecorated(
                MeterInteractorImpl(MeterPresenterImpl(MeterViewValidateDecorated(this)), SaviorLocalFileImpl(File(context.filesDir.path.toString() +"/mood")), GifRepository()))
    }

    override fun onStart() {
        super.onStart()
        interactor.avgMoods(7)
    }

    companion object {
        fun newInstance(): MeterFragment {
            val fragment = MeterFragment()
            return fragment
        }
    }

    override fun success(meterViewModel: MeterViewModel) {
        val avgTxt = rootView.findViewById(R.id.textView_score_avg) as TextView
        avgTxt.text = meterViewModel.avg
        val gifTxt = rootView.findViewById(R.id.textView_gif) as TextView
        gifTxt.text = getString(meterViewModel.sentence)
        val cardGif = rootView.findViewById(R.id.card_gif) as CardView
        cardGif.background = ResourcesCompat.getDrawable(resources, meterViewModel.background, null)

        avgTxt.background = ResourcesCompat.getDrawable(resources, meterViewModel.color, null)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ResourcesCompat.getColor(rootView.resources, meterViewModel.background, null)
        }
    }

    override fun displayGif(gifUrl: String) {
        val gifImage = rootView.findViewById(R.id.imageview_gif) as ImageView
        Glide.with(this).load(gifUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(gifImage)

        gifImage.setOnClickListener {
            interactor.findGif()
        }
    }

    override fun fail() {
    }

    override fun firstStart() {

    }
}