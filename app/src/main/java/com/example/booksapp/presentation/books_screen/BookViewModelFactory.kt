package com.example.booksapp.presentation.books_screen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookViewModelFactory(
    private val categoryId: String,
    private val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BooksViewModel(categoryId, application) as T
    }
}