package com.example.booksapp.presentation.books_screen

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.booksapp.R
import com.example.booksapp.domain.models.BookItem

@Composable
fun BooksScreen(categoryId: String) {
    val viewModel: BooksViewModel = viewModel(
        factory = BookViewModelFactory(
            categoryId,
            LocalContext.current.applicationContext as Application
        )
    )

    val screenState = viewModel.booksFlow.collectAsState(BooksScreenState.Initial)

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
                items(stateValue.books) {
                    BookInfoCard(bookItem = it)
                }
            }
        }

        else -> {}
    }
}

@Composable
fun BookInfoCard(bookItem: BookItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4)
    ) {
        Row {
            AsyncImage(
                modifier = Modifier.height(170.dp),
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.TopStart,
                model = bookItem.imageUrl,
                contentDescription = stringResource(id = R.string.book_image)
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}