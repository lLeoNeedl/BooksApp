package com.example.booksapp.domain.usecases

import com.example.booksapp.domain.models.BookItem
import com.example.booksapp.domain.repository.BooksRepository

class LoadBooksUseCase(private val repository: BooksRepository) {

    suspend operator fun invoke(categoryId: String): List<BookItem> =
        repository.loadBooksByCategory(categoryId)
}