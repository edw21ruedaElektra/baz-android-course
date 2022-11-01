package com.example.wizeline.utils

import com.example.wizeline.data.datasource.models.*

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

fun BidsAndAsksResponse.toBidsAndAsks(): BidsAndAsksList {
    return BidsAndAsksList(
        payload.asks,
        payload.bids
    )
}