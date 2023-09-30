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
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

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