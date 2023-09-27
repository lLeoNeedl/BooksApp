package com.example.booksapp.domain.repository

import com.example.booksapp.domain.models.BookItem
import com.example.booksapp.domain.models.CategoryItem

interface BooksRepository {

    suspend fun loadCategories(): List<CategoryItem>

    suspend fun loadBooksByCategory(categoryId: String): List<BookItem>
}