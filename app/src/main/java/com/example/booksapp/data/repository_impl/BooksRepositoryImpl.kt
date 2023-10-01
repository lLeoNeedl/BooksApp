package com.example.booksapp.data.repository_impl

import android.util.Log
import com.example.booksapp.data.db.AppDatabase
import com.example.booksapp.data.mapper.ItemsMapper
import com.example.booksapp.data.network.ApiService
import com.example.booksapp.domain.models.BookItem
import com.example.booksapp.domain.repository.BooksRepository
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: AppDatabase,
    private val mapper: ItemsMapper
): BooksRepository {

    override suspend fun loadBooksByCategory(categoryId: String): List<BookItem> {
        return try {
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

    private suspend fun getBooksFromCache(categoryId: String): List<BookItem> {
        val entities = db.booksDao().getBooksByCategory(categoryId)
        return entities.map {
            mapper.mapBookEntityToModel(it)
        }
    }
}