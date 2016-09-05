package com.example.octo_sdu.moodometer.listMood.view

import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.octo_sdu.moodometer.R

class ListFragmentViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val textViewMind = itemView!!.findViewById(R.id.textView_mind) as TextView
    val textViewScore = itemView!!.findViewById(R.id.textView_score) as TextView
    val textViewDate = itemView!!.findViewById(R.id.textView_date) as TextView

    fun bind(moodViewModel: MoodViewModel) {
        textViewMind.text = moodViewModel.mind
        textViewScore.text = moodViewModel.score
        textViewDate.text = moodViewModel.date
        textViewScore.background = ResourcesCompat.getDrawable(itemView!!.resources, moodViewModel.color, null)
    }
}
