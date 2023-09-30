package com.example.booksapp.di

import androidx.lifecycle.ViewModel
import com.example.booksapp.presentation.books_screen.BooksViewModel
import com.example.booksapp.presentation.categories_screen.CategoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    @Binds
    fun bindCategoriesViewModel(viewModel: CategoriesViewModel): ViewModel

    @IntoMap
    @ViewModelKey(BooksViewModel::class)
    @Binds
    fun bindBooksViewModel(viewModel: BooksViewModel): ViewModel
}