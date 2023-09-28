package com.example.booksapp.domain.usecases

import com.example.booksapp.domain.models.BookItem
import com.example.booksapp.domain.repository.ContentRepository

class LoadBooksUseCase(private val repository: ContentRepository) {

    suspend operator fun invoke(categoryId: String): List<BookItem> =
        repository.loadBooksByCategory(categoryId)
}