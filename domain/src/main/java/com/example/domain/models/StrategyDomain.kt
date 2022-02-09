package com.example.domain.models

data class StrategyDomain(
    val name: String,
    val description: String,
    val isSaved: Boolean = false,
    val imageUrl: String,
    val id: Int
)
