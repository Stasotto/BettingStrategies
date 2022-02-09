package com.example.bettingstrategies.presentation.models

data class Strategy(
    val name: String,
    val description: String,
    val isSaved: Boolean = false,
    val imageUrl: String,
    val id: Int
)