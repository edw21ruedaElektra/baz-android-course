package com.example.wizeline.utils

import com.example.wizeline.data.datasource.models.AvailableBooksResponse
import com.example.wizeline.data.datasource.models.Book
import com.example.wizeline.data.datasource.models.BookInfoEntity

fun BookInfoEntity.toBook() = Book(
    id = this.book.orEmpty()
)

fun AvailableBooksResponse.toBooks() : List<BookInfoEntity>{
    val books = mutableListOf<BookInfoEntity>()
    this.payload.forEach {
        books.add(it)
    }
    return books
}