package com.example.booksapp.data.repository_impl

import android.app.Application
import android.util.Log
import com.example.booksapp.data.db.AppDatabase
import com.example.booksapp.data.mapper.BookItemsMapper
import com.example.booksapp.data.network.ApiFactory
import com.example.booksapp.domain.models.BookItem
import com.example.booksapp.domain.models.CategoryItem
import com.example.booksapp.domain.repository.BooksRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class BooksRepositoryImpl(application: Application) : BooksRepository {

    private val apiService = ApiFactory.apiService
    private val db = AppDatabase.getInstance(application)
    private val mapper = BookItemsMapper()

    override suspend fun loadCategories(): List<CategoryItem> = coroutineScope {
        try {
            val categoriesResponse = apiService.loadBestSellersCategoriesNames()
            launch {
                val entities = categoriesResponse?.categories?.map {
                    mapper.mapCategoryDtoToEntity(it)
                } ?: listOf()
                db.categoriesDao().saveAllCategories(entities)
            }
            categoriesResponse?.categories?.map { mapper.mapCategoryDtoToModel(it) }
                ?: getCategoriesFromCache()
        } catch (e: Exception) {
            Log.e("BooksRepositoryImpl", e.message.toString())
            listOf()
        }
    }

    override suspend fun loadBooksByCategory(categoryId: String): List<BookItem> = coroutineScope {
        try {
            val booksResponse = apiService.loadBooksByCategory(categoryId)
            launch {
                val entities = booksResponse?.categoryWithBooks?.books?.map {
                    mapper.mapBookItemDtoToEntity(it, categoryId)
                } ?: listOf()
                db.booksDao().saveBooks(entities)
            }
            booksResponse?.categoryWithBooks?.books?.map {
                mapper.mapBookItemDtoToModel(it)
            } ?: getBooksFromCache(categoryId)
        } catch (e: Exception) {
            Log.e("BooksRepositoryImpl", e.message.toString())
            listOf()
        }
    }

    private suspend fun getCategoriesFromCache(): List<CategoryItem> {
        val entities = db.categoriesDao().getAllCategories()
        return entities.map {
            mapper.mapCategoryEntityToModel(it)
        }
    }

    private suspend fun getBooksFromCache(categoryId: String): List<BookItem> {
        val entities = db.booksDao().getBooksByCategory(categoryId)
        return entities.map {
            mapper.mapBookEntityToModel(it)
        }
    }
}