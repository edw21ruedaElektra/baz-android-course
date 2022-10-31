package com.example.wizeline.data

import com.example.wizeline.data.service.BaseService
import com.example.wizeline.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient())
    .build()

val service = retrofit.create(BaseService::class.java)