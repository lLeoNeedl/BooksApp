package com.example.booksapp.data.repository_impl

import android.app.Application
import android.util.Log
import com.example.booksapp.data.db.AppDatabase
import com.example.booksapp.data.mapper.ItemsMapper
import com.example.booksapp.data.network.ApiFactory
import com.example.booksapp.data.network.ApiService
import com.example.booksapp.domain.models.BookItem
import com.example.booksapp.domain.models.CategoryItem
import com.example.booksapp.domain.repository.ContentRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: AppDatabase,
    private val mapper: ItemsMapper
) : ContentRepository {

    override suspend fun loadCategories(): List<CategoryItem> = coroutineScope {
        try {
            val categoriesResponse = try {
                apiService.loadBestSellersCategoriesNames()
            } catch (e: Exception) {
                Log.e("BooksRepositoryImpl", e.message.toString())
                null
            }

            val entities = categoriesResponse?.categories?.map {
                mapper.mapCategoryDtoToEntity(it)
            }
            entities?.let { db.categoriesDao().saveAllCategories(it) }

            categoriesResponse?.categories?.map { mapper.mapCategoryDtoToModel(it) }
                ?: getCategoriesFromCache()
        } catch (e: Exception) {
            Log.e("BooksRepositoryImpl", e.message.toString())
            listOf()
        }
    }

    override suspend fun loadBooksByCategory(categoryId: String): List<BookItem> = coroutineScope {
        try {
            val booksResponse = try {
                apiService.loadBooksByCategory(categoryId)
            } catch (e: Exception) {
                Log.e("BooksRepositoryImpl", e.message.toString())
                null
            }

            val entities = booksResponse?.categoryWithBooks?.books?.map {
                mapper.mapBookItemDtoToEntity(it, categoryId)
            }
            entities?.let { db.booksDao().saveBooks(it) }

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