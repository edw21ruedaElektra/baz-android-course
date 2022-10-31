package com.example.wizeline.data.datasource.models

import com.google.gson.annotations.SerializedName

data class AvailableBooksResponse(
    val success : Boolean,
    @SerializedName("payload")
    val payload: ArrayList<BookInfoEntity>,
)

data class BookInfoEntity(
    val book : String?,
    @SerializedName("minimum_amount")
    val miniumAmount : String,
    @SerializedName("maximum_amount")
    val maxiumAmount : String,
    @SerializedName("minimum_price")
    val miniumPrice : String,
    @SerializedName("maximum_price")
    val maximumPrice : String,
    @SerializedName("minimum_value")
    val miniumValue : String,
    @SerializedName("maximum_value")
    val maximumValue : String
)

data class Book(
    val id : String?
)
