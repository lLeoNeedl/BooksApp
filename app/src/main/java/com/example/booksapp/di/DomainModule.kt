package com.example.booksapp.di

import com.example.booksapp.data.repository_impl.ContentRepositoryImpl
import com.example.booksapp.domain.repository.ContentRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun bindRepository(impl: ContentRepositoryImpl): ContentRepository {
        return impl
    }
}