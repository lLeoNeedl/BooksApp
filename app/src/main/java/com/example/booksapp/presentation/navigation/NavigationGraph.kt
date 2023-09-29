package com.example.booksapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val DEFAULT_VALUE_STR = ""

@Composable
fun NavigationGraph(
    navController: NavHostController,
    categoriesScreenContent: @Composable () -> Unit,
    booksScreenContent: @Composable (String) -> Unit,
    webPageScreenContent: @Composable (String) -> Unit
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
                ?: DEFAULT_VALUE_STR
            booksScreenContent(categoryId)
        }
        composable(
            route = Screen.WebPageScreen.route,
            arguments = listOf(
                navArgument(Screen.KEY_URL) {
                    type = NavType.StringType
                }
            )
        ) {
            val url = it.arguments?.getString(Screen.KEY_URL) ?: DEFAULT_VALUE_STR
            webPageScreenContent(url)
        }
    }
}