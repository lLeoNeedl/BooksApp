package com.example.booksapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.booksapp.presentation.books_screen.BooksScreen
import com.example.booksapp.presentation.categories_screen.CategoriesScreen
import com.example.booksapp.presentation.navigation.NavigationGraph
import com.example.booksapp.presentation.navigation.rememberNavigationState
import com.example.booksapp.presentation.web_page_screen.WebPageScreen
import com.example.booksapp.ui.theme.BooksAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   ScreenContainer()
                }
            }
        }
    }
}

@Composable
fun ScreenContainer() {
    val navigationState = rememberNavigationState()
    NavigationGraph(
        navController = navigationState.navHostController,
        categoriesScreenContent = {
            CategoriesScreen(navigationState)
        },
        booksScreenContent = {
            BooksScreen(categoryId = it, navigationState)
        },
        webPageScreenContent = {
            WebPageScreen(url = it)
        }
    )

}