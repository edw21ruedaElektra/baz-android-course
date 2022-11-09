package com.example.wizeline.data.datasource

import com.example.wizeline.data.datasource.models.AvailableBooksResponse
import com.example.wizeline.data.datasource.models.BidsAndAsksResponse
import com.example.wizeline.data.datasource.models.TickerResponse
import com.example.wizeline.data.service.BaseService
import javax.inject.Inject

class RemoteDataSourceImpl@Inject constructor(
    private val service: BaseService
    ): RemoteDataSource {

    override suspend fun getAvailableBooks(): AvailableBooksResponse {
        return service.availableBooks()
    }

    override suspend fun getAsksAndBids(book: String): BidsAndAsksResponse {
        return service.bidsAndAsks(book)
    }

    override suspend fun getTickers(book: String): TickerResponse {
        return service.getTicker(book)
    }
}