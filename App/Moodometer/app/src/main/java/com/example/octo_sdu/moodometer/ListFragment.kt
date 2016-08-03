package com.example.octo_sdu.moodometer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.octo_sdu.moodometer.listMood.interactor.ListInteractorDecorated
import com.example.octo_sdu.moodometer.listMood.interactor.ListInteractorImpl
import com.example.octo_sdu.moodometer.listMood.presenter.ListPresenterImpl
import com.example.octo_sdu.moodometer.listMood.view.ListFragmentAdapter
import com.example.octo_sdu.moodometer.listMood.view.ListViewValidate
import com.example.octo_sdu.moodometer.listMood.view.ListViewValidateDecorated
import com.example.octo_sdu.moodometer.repository.SaviorLocalFileImpl

class ListFragment: Fragment(), ListViewValidate {

    var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_main_list, container, false)

        ListInteractorDecorated(ListInteractorImpl(ListPresenterImpl(ListViewValidateDecorated(this)), SaviorLocalFileImpl(rootView!!.context))).getMoods()

        return rootView
    }

    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }

    override fun success(listMind: List<String>, listScore: List<String>, listDate: List<String>, whatColor: List<Int>) {
        val recyclerView = rootView!!.findViewById(R.id.recyclerView_moods) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(rootView!!.context)
        recyclerView.adapter = ListFragmentAdapter(listMind, listScore, listDate, whatColor)
    }

    override fun fail() {

    }
}