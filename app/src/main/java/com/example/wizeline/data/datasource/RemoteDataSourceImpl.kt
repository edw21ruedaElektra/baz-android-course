package com.example.wizeline.data.datasource

import com.example.wizeline.data.datasource.models.AvailableBooksResponse
import com.example.wizeline.data.datasource.models.BidsAndAsksResponse
import com.example.wizeline.data.service.BaseService

class RemoteDataSourceImpl(
    private val service: BaseService
    ): RemoteDataSource {

    override suspend fun getAvailableBooks(): AvailableBooksResponse {
        return service.availableBooks()

    }

    override suspend fun getAsksAndBids(book: String): BidsAndAsksResponse {
        return service.bidsAndAsks(book)
    }
}