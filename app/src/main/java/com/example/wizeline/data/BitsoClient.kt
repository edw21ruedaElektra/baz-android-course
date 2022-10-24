package com.example.wizeline.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {
    const val BASE_URL = "https://api.bitso.com/v3/"
}

var retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient())
    .build()

//val service = retrofit.create(Bitso)