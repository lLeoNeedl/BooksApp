package com.example.booksapp.data.mapper

import com.example.booksapp.data.db.entities.BookEntity
import com.example.booksapp.data.db.entities.CategoryEntity
import com.example.booksapp.data.network.dto.BookItemDto
import com.example.booksapp.data.network.dto.CategoryItemDto
import com.example.booksapp.domain.models.BookItem
import com.example.booksapp.domain.models.CategoryItem

class ItemsMapper {

    fun mapCategoryDtoToEntity(dto: CategoryItemDto) = CategoryEntity(
        categoryId = dto.categoryId ?: DEFAULT_VALUE_STR,
        name = dto.name ?: DEFAULT_VALUE_STR,
        publishedDate = dto.publishedDate ?: DEFAULT_VALUE_STR
    )

    fun mapBookItemDtoToEntity(dto: BookItemDto, categoryId: String) = BookEntity(
        title = dto.title ?: DEFAULT_VALUE_STR,
        categoryId = categoryId,
        description = dto.description ?: DEFAULT_VALUE_STR,
        author = dto.author ?: DEFAULT_VALUE_STR,
        publisher = dto.publisher ?: DEFAULT_VALUE_STR,
        imageUrl = dto.imageUrl ?: DEFAULT_VALUE_STR,
        rank = dto.rank ?: DEFAULT_VALUE_STR,
        productUrl = dto.productUrl ?: DEFAULT_VALUE_STR
    )

    fun mapCategoryDtoToModel(dto: CategoryItemDto) = CategoryItem(
        categoryId = dto.categoryId ?: DEFAULT_VALUE_STR,
        name = dto.name ?: DEFAULT_VALUE_STR,
        publishedDate = dto.publishedDate ?: DEFAULT_VALUE_STR
    )

    fun mapBookItemDtoToModel(dto: BookItemDto) = BookItem(
        title = dto.title ?: DEFAULT_VALUE_STR,
        description = dto.description ?: DEFAULT_VALUE_STR,
        author = dto.author ?: DEFAULT_VALUE_STR,
        publisher = dto.publisher ?: DEFAULT_VALUE_STR,
        imageUrl = dto.imageUrl ?: DEFAULT_VALUE_STR,
        rank = dto.rank ?: DEFAULT_VALUE_STR,
        productUrl = dto.productUrl ?: DEFAULT_VALUE_STR
    )

    fun mapCategoryEntityToModel(entity: CategoryEntity) = CategoryItem(
        categoryId = entity.categoryId,
        name = entity.name,
        publishedDate = entity.publishedDate
    )

    fun mapBookEntityToModel(entity: BookEntity) = BookItem(
        title = entity.title,
        description = entity.description,
        author = entity.author,
        publisher = entity.publisher,
        imageUrl = entity.imageUrl,
        rank = entity.rank,
        productUrl = entity.productUrl
    )

    companion object {
        private const val DEFAULT_VALUE_STR = ""
    }
}