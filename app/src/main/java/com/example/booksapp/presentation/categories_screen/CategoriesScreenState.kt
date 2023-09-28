package com.example.booksapp.presentation.categories_screen

import com.example.booksapp.domain.models.CategoryItem

sealed class CategoriesScreenState {

    object Initial: CategoriesScreenState()

    object Loading: CategoriesScreenState()

    data class Loaded(val categories: List<CategoryItem>): CategoriesScreenState()
}