package com.example.booksapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavigationGraph(
    navController: NavHostController,
    categoriesScreenContent: @Composable () -> Unit,
    booksScreenContent: @Composable (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CategoriesScreen.route
    ) {
        composable(Screen.CategoriesScreen.route) {
            categoriesScreenContent()
        }
        composable(
            route = Screen.BooksScreen.route,
            arguments = listOf(
                navArgument(Screen.KEY_CATEGORY_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val categoryId = it.arguments?.getString(Screen.KEY_CATEGORY_ID)
                ?: throw IllegalArgumentException("Category id is null")
            booksScreenContent(categoryId)
        }
    }
}