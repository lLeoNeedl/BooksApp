package com.example.booksapp.di

import android.app.Application
import com.example.booksapp.data.db.AppDatabase
import com.example.booksapp.data.network.ApiFactory
import com.example.booksapp.data.network.ApiService
import com.example.booksapp.data.repository_impl.BooksRepositoryImpl
import com.example.booksapp.data.repository_impl.CategoriesRepositoryImpl
import com.example.booksapp.domain.repository.BooksRepository
import com.example.booksapp.domain.repository.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindCategoriesRepository(impl: CategoriesRepositoryImpl): CategoriesRepository

    @Singleton
    @Binds
    fun bindBooksRepository(impl: BooksRepositoryImpl): BooksRepository

    companion object {
        @Singleton
        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService

        @Singleton
        @Provides
        fun provideDataBase(application: Application): AppDatabase =
            AppDatabase.getInstance(application)
    }
}