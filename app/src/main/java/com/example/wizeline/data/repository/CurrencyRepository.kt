package com.example.wizeline.data.repository

import com.example.wizeline.data.datasource.RemoteDataSource
import com.example.wizeline.data.datasource.models.*
import com.example.wizeline.utils.toBidsAndAsks
import com.example.wizeline.utils.toBooks

class CurrencyRepositoryy (
    private val remoteDataSource: RemoteDataSource
    ) {
    suspend fun fetchAvailableBooks(): List<BookInfoEntity> {
        return remoteDataSource.getAvailableBooks().toBooks()
    }
}

interface CurrencyRepository {
    suspend fun fetchAvailableBooks(): List<BookInfoEntity>
    suspend fun getAsksAndBids(book:String): BidsAndAsksList
}