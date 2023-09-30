package com.example.booksapp.presentation.books_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booksapp.presentation.navigation.NavigationState

@Composable
fun BooksScreen(viewModel: BooksViewModel, navigationState: NavigationState) {

    val screenState = viewModel.booksFlow.collectAsState(BooksScreenState.Initial)

    BooksScreenContent(screenState, navigationState)
}

@Composable
fun BooksScreenContent(screenState: State<BooksScreenState>, navigationState: NavigationState) {
    when (val stateValue = screenState.value) {
        is BooksScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(56.dp)
                )
            }
        }

        is BooksScreenState.Loaded -> {
            LazyColumn(
                contentPadding = PaddingValues(18.dp)
            ) {
                items(
                    items = stateValue.books
                ) {
                    BookInfoCard(bookItem = it, navigationState)
                }
            }
        }

        else -> {}
    }
}