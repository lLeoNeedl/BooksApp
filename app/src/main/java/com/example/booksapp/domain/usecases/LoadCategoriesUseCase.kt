package com.example.booksapp.domain.usecases

import com.example.booksapp.domain.models.CategoryItem
import com.example.booksapp.domain.repository.BooksRepository

class LoadCategoriesUseCase(private val repository: BooksRepository) {

    suspend operator fun invoke(): List<CategoryItem> = repository.loadCategories()
}