package com.example.wizeline.data.repository

import com.example.wizeline.data.datasource.RemoteDataSource
import com.example.wizeline.data.datasource.models.Book
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.utils.toBooks

class CurrencyRepository (
    private val remoteDataSource: RemoteDataSource
    ) {
    suspend fun fetchAvailableBooks(): List<BookInfoEntity> {
        return remoteDataSource.getAvailableBooks().toBooks()
    }
}