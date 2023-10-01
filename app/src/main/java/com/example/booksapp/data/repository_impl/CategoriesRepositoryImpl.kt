package com.example.booksapp.data.repository_impl

import android.util.Log
import com.example.booksapp.data.db.AppDatabase
import com.example.booksapp.data.mapper.ItemsMapper
import com.example.booksapp.data.network.ApiService
import com.example.booksapp.domain.models.CategoryItem
import com.example.booksapp.domain.repository.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: AppDatabase,
    private val mapper: ItemsMapper
) : CategoriesRepository {

    override suspend fun loadCategories(): List<CategoryItem> {
        return try {
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

    private suspend fun getCategoriesFromCache(): List<CategoryItem> {
        val entities = db.categoriesDao().getAllCategories()
        return entities.map {
            mapper.mapCategoryEntityToModel(it)
        }
    }
}