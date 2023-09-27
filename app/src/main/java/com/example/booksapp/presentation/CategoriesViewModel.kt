package com.example.booksapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.data.network.ApiFactory
import kotlinx.coroutines.launch

class CategoriesViewModel: ViewModel() {

    fun loadCategories() {
        viewModelScope.launch {
            val response = ApiFactory.apiService.loadBestSellersCategoriesNames()
            Log.d("CategoriesViewModel", response.categories.toString())
        }
    }

    fun loadBooks(categoryId: String) {
        viewModelScope.launch {
            val response = ApiFactory.apiService.loadBooksByCategory(categoryId)
            Log.d("CategoriesViewModel", response.toString())
        }
    }
}