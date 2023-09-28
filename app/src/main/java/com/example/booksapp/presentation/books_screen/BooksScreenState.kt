package com.example.booksapp.presentation.books_screen

import com.example.booksapp.domain.models.BookItem

sealed class BooksScreenState {

    object Initial: BooksScreenState()

    object Loading: BooksScreenState()

    data class Loaded(val books: List<BookItem>): BooksScreenState()
}