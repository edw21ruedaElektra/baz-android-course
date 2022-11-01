package com.example.wizeline.data.service

import com.example.wizeline.data.datasource.models.AvailableBooksResponse
import com.example.wizeline.data.datasource.models.BidsAndAsksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseService {

    @GET("available_books")
    suspend fun availableBooks(): AvailableBooksResponse

    @GET("order_book")
    suspend fun bidsAndAsks(
        @Query("book") book: String
    ): BidsAndAsksResponse

}