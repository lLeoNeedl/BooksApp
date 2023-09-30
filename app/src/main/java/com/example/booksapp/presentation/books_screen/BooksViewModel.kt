package com.example.booksapp.presentation.books_screen

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

    fun loadBooks(categoryId: String) {
        _booksFlow.value = BooksScreenState.Loading
        viewModelScope.launch {
            val books = loadBooksUseCase(categoryId)
            _booksFlow.value = BooksScreenState.Loaded(books)
        }
    }
}