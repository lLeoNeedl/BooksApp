package com.example.booksapp.presentation.books_screen

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.booksapp.R
import com.example.booksapp.domain.models.BookItem
import com.example.booksapp.presentation.WebPage
import com.example.booksapp.presentation.navigation.NavigationState
import com.example.booksapp.presentation.navigation.Screen

@Composable
fun BooksScreen(categoryId: String, navigationState: NavigationState) {
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
                    BookInfoCard(bookItem = it, navigationState)
                }
            }
        }

        else -> {}
    }
}

@Composable
fun BookInfoCard(bookItem: BookItem, navigationState: NavigationState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4)
    ) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F),
                contentScale = ContentScale.Crop,
                model = bookItem.imageUrl,
                contentDescription = stringResource(id = R.string.book_image)
            )
            Column(
                modifier = Modifier
                    .weight(2F)
                    .padding(12.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = bookItem.title,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.SansSerif,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = bookItem.description,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                            append("Author: ")
                        }
                        append(bookItem.author)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                            append("Publisher: ")
                        }
                        append(bookItem.author)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigationState.navigate(
                                Screen.WebPageScreen.getRouteWithArgs(bookItem.productUrl)
                            )
                        },
                    text = bookItem.productUrl,
                    fontWeight = FontWeight.Light,
                    textDecoration = TextDecoration.Underline,
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}