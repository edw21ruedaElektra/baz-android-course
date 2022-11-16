package com.example.wizeline.data.repository

import com.example.wizeline.data.datasource.models.*
import com.example.wizeline.database.models.BookEntity
import io.reactivex.rxjava3.core.Single

interface CurrencyRepository {
    suspend fun fetchAvailableBooks(): List<BookInfoEntity>
    suspend fun getAsksAndBids(book:String): BidsAndAsksList
    suspend fun getTicker(book:String): TickerEntity
    fun getTickerRX(book:String): Single<TickerEntity>
    suspend fun insertBooks(books: List<BookEntity>)

}