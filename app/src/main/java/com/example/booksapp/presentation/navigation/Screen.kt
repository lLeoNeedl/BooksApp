package com.example.booksapp.presentation.navigation


sealed class Screen(
    val route: String
) {

    object CategoriesScreen : Screen(CATEGORIES_SCREEN_ROUTE)
    object BooksScreen : Screen(BOOKS_SCREEN_ROUTE) {

        private const val ROUTE_FOR_ARGS = "books_screen"
        fun getRouteWithArgs(categoryId: String) = "$ROUTE_FOR_ARGS/${categoryId}"
     }


    companion object {

        const val KEY_CATEGORY_ID = "category_id"

        private const val CATEGORIES_SCREEN_ROUTE = "categories_screen"
        private const val BOOKS_SCREEN_ROUTE = "books_screen/{$KEY_CATEGORY_ID}"
    }
}

