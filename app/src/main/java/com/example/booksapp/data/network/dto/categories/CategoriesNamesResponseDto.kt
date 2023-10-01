package com.example.booksapp.data.network.dto.categories

import com.google.gson.annotations.SerializedName

data class CategoriesNamesResponseDto(
    @SerializedName("results")
    val categories: List<CategoryItemDto>? = null
)