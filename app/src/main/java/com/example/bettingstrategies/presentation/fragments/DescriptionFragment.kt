package com.example.bettingstrategies.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.bettingstrategies.R
import com.example.bettingstrategies.databinding.FragmentDescriptionBinding
import com.example.bettingstrategies.presentation.MainActivity
import com.example.bettingstrategies.presentation.models.Strategy
import com.example.bettingstrategies.presentation.viewmodels.StrategiesFragViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DescriptionFragment(private val strategy: Strategy) :
    Fragment(R.layout.fragment_description) {
    companion object {
        const val TAG = "Description"

        @JvmStatic
        fun newInstance(strategy: Strategy) = DescriptionFragment(strategy)
    }

    private val viewModel: StrategiesFragViewModel by sharedViewModel()

    private val binding: FragmentDescriptionBinding by viewBinding(FragmentDescriptionBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            name.text = strategy.name
            description.text = strategy.description
            loadImage(strategy.imageUrl)
            if (strategy.isSaved) {
                save.setImageResource(R.drawable.ic_baseline_favorite)
            } else {
                save.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            save.setOnClickListener {
                if (strategy.isSaved) {
                    save.setImageResource(R.drawable.ic_baseline_favorite_24)
                    viewModel.deleteStrategy(strategy)
                } else {
                    save.setImageResource(R.drawable.ic_baseline_favorite)
                    viewModel.saveStrategy(strategy)
                }
            }
        }
        (requireActivity() as MainActivity).supportActionBar?.title = "Description"
    }

    private fun loadImage(url: String) {
        Glide.with(binding.image.context)
            .load(url)
            .into(binding.image)
    }

    override fun onDetach() {
        super.onDetach()
        (requireActivity() as MainActivity).supportActionBar?.title = "Batting Strategies"
    }
}