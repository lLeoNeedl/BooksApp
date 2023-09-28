package com.example.booksapp.domain.usecases

import com.example.booksapp.domain.models.CategoryItem
import com.example.booksapp.domain.repository.ContentRepository

class LoadCategoriesUseCase(private val repository: ContentRepository) {

    suspend operator fun invoke(): List<CategoryItem> = repository.loadCategories()
}