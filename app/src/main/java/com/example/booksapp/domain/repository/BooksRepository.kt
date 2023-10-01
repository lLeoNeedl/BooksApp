package com.example.booksapp.domain.repository

import com.example.booksapp.domain.models.BookItem

interface BooksRepository {

    suspend fun loadBooksByCategory(categoryId: String): List<BookItem>
}