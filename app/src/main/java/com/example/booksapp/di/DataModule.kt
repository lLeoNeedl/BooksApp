package com.example.booksapp.di

import android.app.Application
import com.example.booksapp.data.db.AppDatabase
import com.example.booksapp.data.network.ApiFactory
import com.example.booksapp.data.network.ApiService
import com.example.booksapp.data.repository_impl.ContentRepositoryImpl
import com.example.booksapp.domain.repository.ContentRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: ContentRepositoryImpl): ContentRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService

        @ApplicationScope
        @Provides
        fun provideDataBase(application: Application): AppDatabase =
            AppDatabase.getInstance(application)
    }
}