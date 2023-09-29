package com.example.booksapp.presentation.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets


sealed class Screen(
    val route: String
) {

    object CategoriesScreen : Screen(CATEGORIES_SCREEN_ROUTE)
    object BooksScreen : Screen(BOOKS_SCREEN_ROUTE) {

        private const val ROUTE_FOR_ARGS = "books_screen"
        fun getRouteWithArgs(categoryId: String) = "$ROUTE_FOR_ARGS/${categoryId}"
    }

    object WebPageScreen : Screen(WEB_SCREEN_ROUTE) {

        private const val ROUTE_FOR_ARGS = "web_screen"
        fun getRouteWithArgs(url: String): String {
            val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
            return "$ROUTE_FOR_ARGS/${encodedUrl}"
        }
    }


    companion object {

        const val KEY_CATEGORY_ID = "category_id"
        const val KEY_URL = "url"

        private const val CATEGORIES_SCREEN_ROUTE = "categories_screen"
        private const val BOOKS_SCREEN_ROUTE = "books_screen/{$KEY_CATEGORY_ID}"
        private const val WEB_SCREEN_ROUTE = "web_screen/{$KEY_URL}"
    }
}

