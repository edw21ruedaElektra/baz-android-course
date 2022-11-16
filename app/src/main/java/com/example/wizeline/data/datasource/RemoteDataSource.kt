package com.example.wizeline.data.datasource

import com.example.wizeline.data.datasource.models.AvailableBooksResponse
import com.example.wizeline.data.datasource.models.BidsAndAsksResponse
import com.example.wizeline.data.datasource.models.TickerResponse
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    suspend fun getAvailableBooks(): AvailableBooksResponse
    suspend fun getAsksAndBids(book:String): BidsAndAsksResponse
    suspend fun getTickers(book:String): TickerResponse
    fun getTickersRX(book:String): Single<TickerResponse>
}