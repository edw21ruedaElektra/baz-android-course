package com.example.wizeline.data.repository

import com.example.wizeline.data.datasource.RemoteDataSource
import com.example.wizeline.data.datasource.models.BidsAndAsksList
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.data.datasource.models.TickerEntity
import com.example.wizeline.utils.toBidsAndAsks
import com.example.wizeline.utils.toBooks
import com.example.wizeline.utils.toTickerEntity
import javax.inject.Inject

class CurrencyRepositoryImpl  @Inject constructor(
    private val dataSource: RemoteDataSource
    ): CurrencyRepository{
    override suspend fun fetchAvailableBooks(): List<BookInfoEntity> {
        return dataSource.getAvailableBooks().toBooks()
    }

    override suspend fun getAsksAndBids(book: String): BidsAndAsksList {
        return dataSource.getAsksAndBids(book).toBidsAndAsks()
    }

    override suspend fun getTicker(book: String): TickerEntity {
        return dataSource.getTickers(book).toTickerEntity()
    }

}