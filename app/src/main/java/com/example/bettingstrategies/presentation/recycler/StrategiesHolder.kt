package com.example.bettingstrategies.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bettingstrategies.R
import com.example.bettingstrategies.databinding.ItemStrategyBinding
import com.example.bettingstrategies.presentation.models.Strategy

class StrategiesHolder(
    item: View,
    private val clickListener: ClickListener
) : RecyclerView.ViewHolder(item) {

    companion object {
        @JvmStatic
        fun from(parent: ViewGroup, clickListener: ClickListener) = StrategiesHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_strategy, parent, false),
            clickListener
        )
    }

    private val binding: ItemStrategyBinding by lazy { ItemStrategyBinding.bind(item) }

    fun bind(strategy: Strategy) = with(binding) {
        nameOfStrategy.text = strategy.name
        root.setOnClickListener {
            clickListener.onItemClick(strategy)
        }
        loadImage(strategy.imageUrl)
        if (strategy.isSaved) {
            save.setImageResource(R.drawable.ic_baseline_favorite)
        } else {
            save.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        save.setOnClickListener {
            if (strategy.isSaved) {
                clickListener.onDeleteIconClick(strategy, adapterPosition)
            } else {
                clickListener.onSaveIconClick(strategy, adapterPosition)
            }

        }
    }

    private fun loadImage(url: String) {
        Glide.with(binding.image.context)
            .load(url)
            .into(binding.image)
    }
}
