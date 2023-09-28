package com.example.booksapp.presentation.books_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BooksScreen(categoryId: String) {
    Text(text = "Books $categoryId")
}