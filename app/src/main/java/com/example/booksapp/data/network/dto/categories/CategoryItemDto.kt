package com.example.booksapp.data.network.dto.categories

import com.google.gson.annotations.SerializedName

data class CategoryItemDto(
    @SerializedName("list_name_encoded")
    val categoryId: String? = null,
    @SerializedName("display_name")
    val name: String? = null,
    @SerializedName("newest_published_date")
    val publishedDate: String? = null
)