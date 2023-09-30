package com.example.booksapp.presentation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.booksapp.di.ApplicationComponent
import com.example.booksapp.di.DaggerApplicationComponent

class BooksApp: Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    return (LocalContext.current.applicationContext as BooksApp).component
}