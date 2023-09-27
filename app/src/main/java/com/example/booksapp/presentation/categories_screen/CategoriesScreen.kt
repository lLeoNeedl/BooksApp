package com.example.booksapp.presentation.categories_screen

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booksapp.R
import com.example.booksapp.domain.models.CategoryItem

@Composable
fun CategoriesScreen() {
    val viewModel: CategoriesViewModel = viewModel()
    val screenState = viewModel.categoriesFlow.collectAsState(CategoriesScreenState.Initial)
    val state = screenState.value
    if (state is CategoriesScreenState.Loaded) {
        val categories = state.categories
        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            items(
                items = categories
            ) {
                CategoryItem(categoryItem = it)
            }
        })
    }
}

@Composable
fun CategoryItem(categoryItem: CategoryItem) {
    ElevatedCard(
        modifier = Modifier
            .padding(12.dp)
            .height(180.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(12),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
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
                text = String.format(stringResource(R.string.text_category_publication_date), categoryItem.publishedDate),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}