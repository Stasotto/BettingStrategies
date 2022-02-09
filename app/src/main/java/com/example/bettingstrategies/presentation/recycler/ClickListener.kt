package com.example.bettingstrategies.presentation.recycler

import com.example.bettingstrategies.presentation.models.Strategy

interface ClickListener {

    fun onItemClick(strategy: Strategy)

    fun onSaveIconClick(strategy: Strategy, position: Int)

    fun onDeleteIconClick(strategy: Strategy, position: Int)
}