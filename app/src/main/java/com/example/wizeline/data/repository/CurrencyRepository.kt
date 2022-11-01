package com.example.wizeline.data.repository

import com.example.wizeline.data.datasource.models.*

interface CurrencyRepository {
    suspend fun fetchAvailableBooks(): List<BookInfoEntity>
    suspend fun getAsksAndBids(book:String): BidsAndAsksList
}