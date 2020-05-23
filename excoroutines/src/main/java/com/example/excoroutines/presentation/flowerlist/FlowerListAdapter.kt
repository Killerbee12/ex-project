package com.example.excoroutines.presentation.flowerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.excoroutines.R
import com.example.excoroutines.domain.models.flower.FlowerModel

class FlowerListAdapter(private val flowerList: ArrayList<FlowerModel>) :
    RecyclerView.Adapter<FlowerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_flower_list, parent, false)
        return FlowerViewHolder(view)
    }

    override fun getItemCount(): Int = flowerList.size

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.setView(flowerList[position])
    }

    fun updateFlowerList(list: List<FlowerModel>) {
        flowerList.clear()
        flowerList.addAll(list)
        notifyDataSetChanged()
    }
}