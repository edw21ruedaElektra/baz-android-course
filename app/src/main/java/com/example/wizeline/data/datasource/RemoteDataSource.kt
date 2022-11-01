package com.example.wizeline.data.datasource

import com.example.wizeline.data.datasource.models.AvailableBooksResponse
import com.example.wizeline.data.datasource.models.BidsAndAsksResponse

interface RemoteDataSource {
    suspend fun getAvailableBooks(): AvailableBooksResponse
    suspend fun getAsksAndBids(book:String): BidsAndAsksResponse
}