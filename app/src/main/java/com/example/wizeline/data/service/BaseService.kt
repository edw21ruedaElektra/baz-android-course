package com.example.wizeline.data.service

import com.example.wizeline.data.datasource.models.AvailableBooksResponse
import retrofit2.http.GET

interface BaseService {
    @GET("available_books")
    suspend fun availableBooks(): AvailableBooksResponse

}