package com.example.wizeline.data.repository

import com.example.wizeline.data.datasource.models.*
import com.example.wizeline.database.models.BookEntity

interface CurrencyRepository {
    suspend fun fetchAvailableBooks(): List<BookInfoEntity>
    suspend fun fetchAvailableBooksFlow(): List<BookInfoEntity>

    suspend fun getAsksAndBids(book:String): BidsAndAsksList
    suspend fun getTicker(book:String): TickerEntity
    suspend fun insertBooks(books: List<BookEntity>)

}