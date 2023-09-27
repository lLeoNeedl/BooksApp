package com.example.booksapp.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booksapp.data.db.dao.BooksDao
import com.example.booksapp.data.db.dao.CategoriesDao
import com.example.booksapp.data.db.entities.BookEntity
import com.example.booksapp.data.db.entities.CategoryEntity

@Database(
    entities = [CategoryEntity::class, BookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: AppDatabase? = null
        private val lock = Any()
        private const val DB_NAME = "main.db"

        fun getInstance(application: Application): AppDatabase {
            synchronized(lock) {
                INSTANCE?.let {
                    return it
                }
            }
            val db = Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DB_NAME
            ).build()
            INSTANCE = db
            return db
        }
    }

    abstract fun categoriesDao(): CategoriesDao
    abstract fun booksDao(): BooksDao
}