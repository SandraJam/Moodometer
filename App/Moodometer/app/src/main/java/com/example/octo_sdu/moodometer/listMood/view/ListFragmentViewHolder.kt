package com.example.octo_sdu.moodometer.listMood.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.octo_sdu.moodometer.R

class ListFragmentViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val textviewMind = itemView!!.findViewById(R.id.textView_mind) as TextView
    val textviewScore = itemView!!.findViewById(R.id.textView_score) as TextView
    val textviewDate = itemView!!.findViewById(R.id.textView_date) as TextView
    val relativeLayoutCell = itemView!!.findViewById(R.id.relative_layout_cell) as RelativeLayout

    fun bind(mind: String, score: String, date: String, color: Int) {
        textviewMind.text = mind
        textviewScore.text = score
        textviewDate.text = date
        relativeLayoutCell.setBackgroundResource(color)
    }
}
