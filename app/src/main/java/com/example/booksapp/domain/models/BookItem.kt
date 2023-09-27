package com.example.booksapp.domain.models

data class BookItem(
    val title: String,
    val description: String,
    val author: String,
    val publisher: String,
    val imageUrl: String,
    val rank: String,
    val productUrl: String
)