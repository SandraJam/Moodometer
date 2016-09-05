package com.example.octo_sdu.moodometer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.octo_sdu.moodometer.listMood.DaggerListComponent
import com.example.octo_sdu.moodometer.listMood.ListModule
import com.example.octo_sdu.moodometer.listMood.interactor.ListInteractor
import com.example.octo_sdu.moodometer.listMood.view.ListFragmentAdapter
import com.example.octo_sdu.moodometer.listMood.view.ListViewValidate
import com.example.octo_sdu.moodometer.listMood.view.ListViewValidateDecorated
import com.example.octo_sdu.moodometer.listMood.view.MoodViewModel
import com.example.octo_sdu.moodometer.main.DaggerMainComponent
import com.example.octo_sdu.moodometer.main.MainDependencies
import com.example.octo_sdu.moodometer.main.MainModule
import javax.inject.Inject

class ListFragment: Fragment(), ListViewValidate {

    lateinit var rootView: View
    @Inject
    lateinit var interactor: ListInteractor
    @Inject
    lateinit var listViewValidateDecorated : ListViewValidateDecorated

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_main_list, container, false)
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerListComponent.builder().mainComponent(MainDependencies.instance.mainComponent)
                .build().inject(this)
    }

    override fun onStart() {
        super.onStart()
        listViewValidateDecorated.listViewValidate = this
        interactor.getMoods()
    }

    override fun onStop() {
        listViewValidateDecorated.listViewValidate = null
        super.onStop()
    }

    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }

    override fun success(listMoodViewModel: List<MoodViewModel>) {
        val recyclerView = rootView.findViewById(R.id.recyclerView_moods) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(rootView.context)
        recyclerView.adapter = ListFragmentAdapter(listMoodViewModel)
    }

    override fun fail() {

    }
}