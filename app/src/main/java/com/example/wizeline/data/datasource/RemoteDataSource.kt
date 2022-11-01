package com.example.wizeline.data.datasource

import com.example.wizeline.data.datasource.models.AvailableBooksResponse
import com.example.wizeline.data.datasource.models.BidsAndAsksResponse
import com.example.wizeline.data.service.BaseService



interface RemoteDataSource {
    suspend fun getAvailableBooks(): AvailableBooksResponse
    suspend fun getAsksAndBids(book:String): BidsAndAsksResponse
}