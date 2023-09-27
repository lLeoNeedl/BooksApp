package com.example.booksapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.data.db.AppDatabase
import com.example.booksapp.data.db.entities.BookEntity
import com.example.booksapp.data.db.entities.CategoryEntity
import com.example.booksapp.data.mapper.BookItemsMapper
import com.example.booksapp.data.network.ApiFactory
import kotlinx.coroutines.launch

class CategoriesViewModel(application: Application): AndroidViewModel(application) {

    val db = AppDatabase.getInstance(getApplication())
    val mapper = BookItemsMapper()

    fun loadCategories() {
        viewModelScope.launch {
            val response = ApiFactory.apiService.loadBestSellersCategoriesNames()
            val entities = mutableListOf<CategoryEntity>()
            response.categories?.forEach {
                entities.add(mapper.mapCategoryDtoToEntity(it))
            }
            db.categoriesDao().saveAllCategories(entities)
            Log.d("CategoriesViewModel", db.categoriesDao().getAllCategories().toString())

            Log.d("CategoriesViewModel", response.categories.toString())
        }
    }

    fun loadBooks(categoryId: String) {
        viewModelScope.launch {
            val response = ApiFactory.apiService.loadBooksByCategory(categoryId)
            val entities = mutableListOf<BookEntity>()
            response.categoryWithBooks?.books?.forEach {
                entities.add(mapper.mapBookItemDtoToEntity(it, categoryId))
            }
            db.booksDao().saveBooks(entities)
            Log.d("CategoriesViewModel", db.booksDao().getBooksByCategory(categoryId).toString())
            Log.d("CategoriesViewModel", response.toString())
        }
    }
}