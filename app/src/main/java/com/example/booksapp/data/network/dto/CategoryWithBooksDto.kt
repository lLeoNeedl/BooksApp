package com.example.booksapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class CategoryWithBooksDto(
    @SerializedName("books")
    val books: List<BookItemDto>? = null
)