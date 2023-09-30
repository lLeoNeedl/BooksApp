package com.example.booksapp.presentation.categories_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.domain.usecases.LoadCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val loadCategoriesUseCase: LoadCategoriesUseCase
) : ViewModel() {

    private val _categoriesFlow =
        MutableStateFlow<CategoriesScreenState>(CategoriesScreenState.Initial)
    val categoriesFlow = _categoriesFlow.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _categoriesFlow.value = CategoriesScreenState.Loading
        viewModelScope.launch {
            val result = loadCategoriesUseCase()
            _categoriesFlow.value = CategoriesScreenState.Loaded(result)
        }
    }
}