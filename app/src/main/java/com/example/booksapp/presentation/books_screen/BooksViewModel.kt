package com.example.booksapp.presentation.books_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.data.repository_impl.ContentRepositoryImpl
import com.example.booksapp.domain.usecases.LoadBooksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BooksViewModel(categoryId: String, application: Application): AndroidViewModel(application) {

    private val contentRepositoryImpl = ContentRepositoryImpl(application)
    private val loadBooksUseCase = LoadBooksUseCase(contentRepositoryImpl)

    private val _booksFlow =
        MutableStateFlow<BooksScreenState>(BooksScreenState.Initial)
    val booksFlow = _booksFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _booksFlow.value = BooksScreenState.Loading
            val books = loadBooksUseCase(categoryId)
            _booksFlow.value = BooksScreenState.Loaded(books)
        }
    }
}