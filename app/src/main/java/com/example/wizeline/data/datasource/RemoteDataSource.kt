package com.example.wizeline.data.datasource

import com.example.wizeline.data.datasource.models.AvailableBooksResponse
import com.example.wizeline.data.service.BaseService

class RemoteDataSource (
    private val baseService: BaseService
    ){
    suspend fun getAvailableBooks() : AvailableBooksResponse =
        baseService.availableBooks()
}