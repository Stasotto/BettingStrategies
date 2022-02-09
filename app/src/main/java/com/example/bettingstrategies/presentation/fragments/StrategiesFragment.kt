package com.example.bettingstrategies.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bettingstrategies.R
import com.example.bettingstrategies.databinding.FragmentStrategiesBinding
import com.example.bettingstrategies.presentation.MainActivity
import com.example.bettingstrategies.presentation.models.Strategy
import com.example.bettingstrategies.presentation.recycler.ClickListener
import com.example.bettingstrategies.presentation.recycler.StrategiesAdapter
import com.example.bettingstrategies.presentation.viewmodels.StrategiesFragViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StrategiesFragment : Fragment(R.layout.fragment_strategies) {

    companion object {
        const val TAG = "Strategies"

        fun newInstance() = StrategiesFragment()
    }

    private val clickListener: ClickListener = object : ClickListener {
        override fun onItemClick(strategy: Strategy) {
            openFragment(DescriptionFragment.newInstance(strategy), DescriptionFragment.TAG)
        }

        override fun onSaveIconClick(strategy: Strategy, position: Int) {
            viewModel.saveStrategy(strategy)
            if (binding.back.isVisible) {
                viewModel.loadStrategyFromDB()
            } else {
                viewModel.updateData(position)
                viewModel.loadStrategyFromDB()
            }
            Toast.makeText(requireContext(), "Betting strategy saved", Toast.LENGTH_SHORT)
                .show()
        }

        override fun onDeleteIconClick(strategy: Strategy, position: Int) {
            viewModel.deleteStrategy(strategy)
            if (binding.back.isVisible) {
                viewModel.loadStrategyFromDB()
            } else {
                viewModel.updateData(position)
                viewModel.loadStrategyFromDB()
            }
            Toast.makeText(requireContext(), "Betting strategy removed", Toast.LENGTH_SHORT).show()
        }

    }

    private val binding: FragmentStrategiesBinding by viewBinding(FragmentStrategiesBinding::bind)
    private val viewModel: StrategiesFragViewModel by viewModel()
    private val adapter: StrategiesAdapter by lazy { StrategiesAdapter(clickListener) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updateOnClickBack()
        initRecycler()
    }

    private fun initRecycler() {
        with(binding) {
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(requireContext())

            back.setOnClickListener {
                (requireActivity() as MainActivity).supportActionBar?.title = "Batting Strategies"
                backH.isVisible = false
                back.isVisible = false
                backF.isVisible = true
                showFavorites.isVisible = true
                viewModel.updateOnClickBack()
            }
        }
        observeFavorites()
        observeStrategy()
    }

    private fun observeFavorites() = with(binding) {
        viewModel.favoritesStrategies.observe(viewLifecycleOwner, { favoritesStrategy ->
            if (back.isVisible) {
                adapter.addStrategies(favoritesStrategy)
            }
            showFavorites.setOnClickListener {

                (requireActivity() as MainActivity).supportActionBar?.title = "Favorites"
                backH.isVisible = true
                back.isVisible = true
                backF.isVisible = false
                showFavorites.isVisible = false
                adapter.addStrategies(favoritesStrategy)
            }
        })

    }

    private fun observeStrategy() {
        viewModel.strategiesForRecycler.observe(viewLifecycleOwner, { strategies ->
            adapter.addStrategies(strategies)
        })
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        parentFragmentManager.beginTransaction()
            .addToBackStack(tag)
            .replace(R.id.container, fragment, tag)
            .commit()
    }
}