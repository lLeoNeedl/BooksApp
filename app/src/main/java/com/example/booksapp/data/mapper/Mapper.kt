package com.example.booksapp.data.mapper

import com.example.booksapp.data.db.entities.BookEntity
import com.example.booksapp.data.db.entities.CategoryEntity
import com.example.booksapp.data.network.dto.BookItemDto
import com.example.booksapp.data.network.dto.CategoryItemDto

class Mapper {

    fun mapCategoryDtoToEntity(category: CategoryItemDto) = CategoryEntity(
        categoryId = category.categoryId ?: DEFAULT_VALUE_STR,
        name = category.name ?: DEFAULT_VALUE_STR,
        publishedDate = category.publishedDate ?: DEFAULT_VALUE_STR
    )

    fun mapBookItemDtoToEntity(book: BookItemDto, categoryId: String) = BookEntity(
        title = book.title ?: DEFAULT_VALUE_STR,
        categoryId = categoryId,
        description = book.description ?: DEFAULT_VALUE_STR,
        author = book.author ?: DEFAULT_VALUE_STR,
        publisher = book.publisher ?: DEFAULT_VALUE_STR,
        imageUrl = book.imageUrl ?: DEFAULT_VALUE_STR,
        rank = book.rank ?: DEFAULT_VALUE_STR,
        productUrl = book.productUrl ?: DEFAULT_VALUE_STR
    )

    companion object {
        private const val DEFAULT_VALUE_STR = ""
    }
}