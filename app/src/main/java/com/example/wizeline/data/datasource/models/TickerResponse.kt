package com.example.wizeline.data.datasource.models

import com.google.gson.annotations.SerializedName

data class TickerResponse(
    val success : Boolean,
    @SerializedName("payload")
    val payload: TickerEntity
)
data class TickerEntity(
    val book : String?,
    val volume : String,
    val high : String,
    val last : String,
    val low : String,
    val vwap : String,
    val ask : String,
    val bid : String,
    val created_at : String,

    )
