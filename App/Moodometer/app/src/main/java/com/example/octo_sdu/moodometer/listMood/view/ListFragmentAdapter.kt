package com.example.octo_sdu.moodometer.listMood.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.example.octo_sdu.moodometer.R
import com.example.octo_sdu.moodometer.listMood.view.ListFragmentViewHolder

class ListFragmentAdapter(
        internal var listMoodViewModel: List<MoodViewModel>) : RecyclerView.Adapter<ListFragmentViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): ListFragmentViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_mood, viewGroup, false)
        return ListFragmentViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: ListFragmentViewHolder, position: Int) =
        myViewHolder.bind(listMoodViewModel[position])


    override fun getItemCount(): Int = listMoodViewModel.size

}