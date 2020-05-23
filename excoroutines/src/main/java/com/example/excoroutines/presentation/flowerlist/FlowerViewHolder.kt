package com.example.excoroutines.presentation.flowerlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.excoroutines.domain.models.flower.FlowerModel
import kotlinx.android.synthetic.main.item_flower_list.view.*

class FlowerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setView(data: FlowerModel) {
        view.tvName.text = data.name
    }
}