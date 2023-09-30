package com.example.booksapp.presentation.books_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.domain.usecases.LoadBooksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BooksViewModel @Inject constructor(
    private val loadBooksUseCase: LoadBooksUseCase
) : ViewModel() {

    private val _booksFlow =
        MutableStateFlow<BooksScreenState>(BooksScreenState.Initial)
    val booksFlow = _booksFlow.asStateFlow()

    init {
        _booksFlow.value = BooksScreenState.Loading
    }

    fun loadBooks(categoryId: String) {
        viewModelScope.launch {
            val books = loadBooksUseCase(categoryId)
            _booksFlow.value = BooksScreenState.Loaded(books)
        }
    }
}