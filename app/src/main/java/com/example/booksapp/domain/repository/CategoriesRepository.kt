package com.example.booksapp.domain.repository

import com.example.booksapp.domain.models.CategoryItem

interface CategoriesRepository {

    suspend fun loadCategories(): List<CategoryItem>
}