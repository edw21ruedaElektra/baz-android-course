package com.example.wizeline.utils

import com.example.wizeline.data.datasource.models.*
import io.reactivex.rxjava3.core.Single

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

fun TickerResponse.toTickerEntity(): TickerEntity {
    return payload
}

fun Single<TickerResponse>.toTickerEntity() : Single<TickerEntity>{
    return this.map {
        it.payload
    }
}