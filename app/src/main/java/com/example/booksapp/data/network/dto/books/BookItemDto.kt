package com.example.booksapp.data.network.dto.books

import com.google.gson.annotations.SerializedName

data class BookItemDto(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("publisher")
    val publisher: String? = null,
    @SerializedName("book_image")
    val imageUrl: String? = null,
    @SerializedName("rank")
    val rank: String? = null,
    @SerializedName("amazon_product_url")
    val productUrl: String? = null,
)