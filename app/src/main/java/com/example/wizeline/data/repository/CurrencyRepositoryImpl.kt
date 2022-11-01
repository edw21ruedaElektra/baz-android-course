package com.example.wizeline.data.repository

import com.example.wizeline.data.datasource.RemoteDataSource
import com.example.wizeline.data.datasource.models.BidsAndAsksList
import com.example.wizeline.data.datasource.models.BidsAndAsksResponse
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.utils.toBidsAndAsks
import com.example.wizeline.utils.toBooks

class CurrencyRepositoryImpl (
    private val dataSource: RemoteDataSource
        ) : CurrencyRepository{
    override suspend fun fetchAvailableBooks(): List<BookInfoEntity> {
        return dataSource.getAvailableBooks().toBooks()
    }

    override suspend fun getAsksAndBids(book: String): BidsAndAsksList {
        return dataSource.getAsksAndBids(book).toBidsAndAsks()
    }

}