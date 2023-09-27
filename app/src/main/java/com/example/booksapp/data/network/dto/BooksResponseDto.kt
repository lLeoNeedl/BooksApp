package com.example.booksapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class BooksResponseDto(
    @SerializedName("results")
    val categoryWithBooks: CategoryWithBooksDto? = null
)
