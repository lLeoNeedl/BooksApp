package com.example.booksapp.data.network

import com.example.booksapp.data.network.dto.BooksResponseDto
import com.example.booksapp.data.network.dto.CategoriesNamesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("lists/names.json")
    suspend fun loadBestSellersCategoriesNames(
        @Query("api-key") apiKey: String = API_KEY
    ): CategoriesNamesResponseDto

    @GET("lists/{category}.json")
    suspend fun loadBooksByCategory(
        @Path("category") categoryId: String,
        @Query("api-key") apiKey: String = API_KEY
    ): BooksResponseDto

    companion object {
        private const val API_KEY = "7jvKXG9UsNZbBgziiGONdU7P9DAZAliL"
    }
}