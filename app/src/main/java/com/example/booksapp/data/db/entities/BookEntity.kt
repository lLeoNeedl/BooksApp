package com.example.booksapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("books", primaryKeys = ["title", "categoryId"])
data class BookEntity(
    val title: String,
    val categoryId: String,
    val description: String,
    val author: String,
    val publisher: String,
    val imageUrl: String,
    val rank: String,
    val productUrl: String
)