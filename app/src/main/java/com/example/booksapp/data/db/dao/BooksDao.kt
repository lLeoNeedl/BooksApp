package com.example.booksapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.booksapp.data.db.entities.BookEntity

@Dao
interface BooksDao {

    @Query("SELECT * FROM books WHERE categoryId == :categoryId")
    suspend fun getBooksByCategory(categoryId: String): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBooks(books: List<BookEntity>)
}