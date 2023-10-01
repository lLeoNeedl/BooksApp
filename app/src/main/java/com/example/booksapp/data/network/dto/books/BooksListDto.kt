package com.example.booksapp.data.network.dto.books

import com.google.gson.annotations.SerializedName

data class BooksListDto(
    @SerializedName("books")
    val books: List<BookItemDto>? = null
)