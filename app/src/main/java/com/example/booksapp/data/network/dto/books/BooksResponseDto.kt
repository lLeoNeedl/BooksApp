package com.example.booksapp.data.network.dto.books

import com.google.gson.annotations.SerializedName

data class BooksResponseDto(
    @SerializedName("results")
    val categoryWithBooks: BooksListDto? = null
)
