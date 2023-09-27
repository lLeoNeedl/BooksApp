package com.example.booksapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class CategoryEntity(
    @PrimaryKey
    val categoryId: String,
    val name: String,
    val publishedDate: String
)