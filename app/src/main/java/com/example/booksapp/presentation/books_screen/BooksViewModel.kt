package com.example.booksapp.presentation.books_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.domain.usecases.LoadBooksUseCase
import com.example.booksapp.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loadBooksUseCase: LoadBooksUseCase
) : ViewModel() {

    private val _booksFlow =
        MutableStateFlow<BooksScreenState>(BooksScreenState.Initial)
    val booksFlow = _booksFlow.asStateFlow()

    private val categoryId by lazy {
        savedStateHandle.get<String>(Screen.KEY_CATEGORY_ID) ?: DEFAULT_VALUE_STR
    }

    init {
        loadBooks(categoryId)
    }

    fun putCategoryId(categoryId: String) {
        savedStateHandle[Screen.KEY_CATEGORY_ID] = categoryId
    }

    private fun loadBooks(categoryId: String) {
        _booksFlow.value = BooksScreenState.Loading
        viewModelScope.launch {
            val books = loadBooksUseCase(categoryId)
            _booksFlow.value = BooksScreenState.Loaded(books)
        }
    }

    companion object {
        private const val DEFAULT_VALUE_STR = ""
    }
}