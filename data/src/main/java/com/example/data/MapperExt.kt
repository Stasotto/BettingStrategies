package com.example.data

import com.example.data.models.StrategyEntity
import com.example.domain.models.StrategyDomain

fun StrategyEntity.toStrategyDomain() = StrategyDomain(
    name = name,
    description = description,
    isSaved = isSaved,
    imageUrl = imageUrl,
    id = id
)

fun StrategyDomain.toStrategyEntity() = StrategyEntity(
    name = name,
    description = description,
    isSaved = isSaved,
    imageUrl = imageUrl,
    id = id
)