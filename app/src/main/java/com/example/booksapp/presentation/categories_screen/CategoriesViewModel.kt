package com.example.booksapp.presentation.categories_screen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.data.repository_impl.ContentRepositoryImpl
import com.example.booksapp.domain.usecases.LoadBooksUseCase
import com.example.booksapp.domain.usecases.LoadCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ContentRepositoryImpl(application)
    private val loadCategoriesUseCase = LoadCategoriesUseCase(repository)

    private val _categoriesFlow =
        MutableStateFlow<CategoriesScreenState>(CategoriesScreenState.Initial)
    val categoriesFlow = _categoriesFlow.asStateFlow()

    init {
        viewModelScope.launch {
            loadCategories()
        }
    }

    private suspend fun loadCategories() {
        _categoriesFlow.value = CategoriesScreenState.Loading
        val result = loadCategoriesUseCase()
        _categoriesFlow.value = CategoriesScreenState.Loaded(result)
    }
}