package com.example.bettingstrategies.presentation

import com.example.bettingstrategies.presentation.models.Strategy
import com.example.domain.models.StrategyDomain

fun StrategyDomain.toStrategy() = Strategy(
    name = name,
    description = description,
    isSaved = isSaved,
    imageUrl = imageUrl,
    id = id
)

fun Strategy.toStrategyDomain() = StrategyDomain(
    name = name,
    description = description,
    isSaved = true,
    imageUrl = imageUrl,
    id = id
)