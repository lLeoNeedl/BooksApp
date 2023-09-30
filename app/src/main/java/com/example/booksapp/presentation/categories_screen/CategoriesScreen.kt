package com.example.booksapp.presentation.categories_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booksapp.R
import com.example.booksapp.domain.models.CategoryItem
import com.example.booksapp.presentation.getApplicationComponent
import com.example.booksapp.presentation.navigation.NavigationState
import com.example.booksapp.presentation.navigation.Screen

@Composable
fun CategoriesScreen(navigationState: NavigationState) {
    val component = getApplicationComponent()
    val viewModel: CategoriesViewModel = viewModel(factory = component.getViewModelFactory())
    val screenState = viewModel.categoriesFlow.collectAsState(CategoriesScreenState.Initial)
    when (val stateValue = screenState.value) {
        is CategoriesScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(56.dp)
                )
            }
        }

        is CategoriesScreenState.Loaded -> {
            val categories = stateValue.categories
            LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                items(
                    items = categories
                ) {
                    CategoryItem(categoryItem = it, navigationState)
                }
            })
        }

        else -> {}
    }
}

@Composable
fun CategoryItem(categoryItem: CategoryItem, navigationState: NavigationState) {
    Box(
        modifier = Modifier.aspectRatio(0.8F)
    ) {
        ElevatedCard(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
                .clickable {
                    navigationState.navigate(Screen.BooksScreen.getRouteWithArgs(categoryItem.categoryId))
                },
            shape = RoundedCornerShape(12),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = categoryItem.name,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = String.format(
                        stringResource(R.string.text_category_publication_date),
                        categoryItem.publishedDate
                    ),
                    lineHeight = 18.sp,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}