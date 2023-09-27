package com.example.booksapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.booksapp.data.db.entities.CategoryEntity

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM categories ORDER BY publishedDate")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCategories(categories: List<CategoryEntity>)
}