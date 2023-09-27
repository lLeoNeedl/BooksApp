package com.example.booksapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class CategoryWithBooksDto(
    @SerializedName("list_name_encoded")
    val categoryId: String? = null,
    @SerializedName("books")
    val books: List<BookItemDto>? = null
)