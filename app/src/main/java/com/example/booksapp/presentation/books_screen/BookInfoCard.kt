package com.example.booksapp.presentation.books_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.booksapp.R
import com.example.booksapp.domain.models.BookItem
import com.example.booksapp.presentation.navigation.NavigationState
import com.example.booksapp.presentation.navigation.Screen

@Composable
fun BookInfoCard(bookItem: BookItem, navigationState: NavigationState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Row(
            modifier =
            Modifier.padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = bookItem.imageUrl,
                    contentDescription = stringResource(id = R.string.content_description_book_image),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = CircleShape
                        )
                        .size(64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = bookItem.rank)
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .weight(2F)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = bookItem.title,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = bookItem.description,
                    fontWeight = FontWeight.Light,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                            append(stringResource(R.string.text_author))
                        }
                        append(bookItem.author)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                            append(stringResource(R.string.text_publisher))
                        }
                        append(bookItem.author)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    lineHeight = 20.sp
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
                    lineHeight = 20.sp
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}