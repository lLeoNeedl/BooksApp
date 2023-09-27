package com.example.booksapp.presentation.categories_screen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.data.db.AppDatabase
import com.example.booksapp.data.db.entities.BookEntity
import com.example.booksapp.data.db.entities.CategoryEntity
import com.example.booksapp.data.mapper.BookItemsMapper
import com.example.booksapp.data.network.ApiFactory
import com.example.booksapp.data.repository_impl.BooksRepositoryImpl
import com.example.booksapp.domain.usecases.LoadBooksUseCase
import com.example.booksapp.domain.usecases.LoadCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel(application: Application): AndroidViewModel(application) {

    private val repository = BooksRepositoryImpl(application)
    private val loadCategoriesUseCase = LoadCategoriesUseCase(repository)
    private val loadBooksUseCase = LoadBooksUseCase(repository)

    private val _categoriesFlow =
        MutableStateFlow<CategoriesScreenState>(CategoriesScreenState.Initial)
    val categoriesFlow = _categoriesFlow.asStateFlow()

    init {
        viewModelScope.launch {
            loadCategories()
        }
    }

    private suspend fun loadCategories() {
        val result = loadCategoriesUseCase()
        _categoriesFlow.value = CategoriesScreenState.Loaded(result)
    }

    fun loadBooks(categoryId: String) {
        viewModelScope.launch {
            val result = loadBooksUseCase(categoryId)
            Log.d("CategoriesViewModel", result.toString())
        }
    }
}