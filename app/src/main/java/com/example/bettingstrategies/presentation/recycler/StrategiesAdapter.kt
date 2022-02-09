package com.example.bettingstrategies.presentation.recycler

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bettingstrategies.presentation.models.Strategy

class StrategiesAdapter(
    private val clickListener: ClickListener
) : RecyclerView.Adapter<StrategiesHolder>() {

    private var listOfStrategies = listOf<Strategy>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StrategiesHolder {
        return StrategiesHolder.from(parent, clickListener)
    }

    override fun onBindViewHolder(holder: StrategiesHolder, position: Int) {
        holder.bind(listOfStrategies[position])
    }

    override fun getItemCount(): Int {
        return listOfStrategies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addStrategies(list: List<Strategy>) {
        listOfStrategies = list
        notifyDataSetChanged()
    }
}