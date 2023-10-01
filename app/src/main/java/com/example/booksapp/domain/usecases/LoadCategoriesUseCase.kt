package com.example.booksapp.domain.usecases

import com.example.booksapp.domain.models.CategoryItem
import com.example.booksapp.domain.repository.CategoriesRepository
import javax.inject.Inject

class LoadCategoriesUseCase @Inject constructor(private val repository: CategoriesRepository) {

    suspend operator fun invoke(): List<CategoryItem> = repository.loadCategories()
}