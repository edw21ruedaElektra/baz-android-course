package com.example.wizeline.data.datasource.models

import com.google.gson.annotations.SerializedName

data class BidsAndAsksResponse(
    val success : Boolean,
    @SerializedName("payload")
    val payload: PayloadBidsAndAsks,
)

data class PayloadBidsAndAsks(
    @SerializedName("asks")
    val asks :  ArrayList<BidsAndAsks>,
    @SerializedName("bids")
    val bids :  ArrayList<BidsAndAsks>,
    @SerializedName("updated_at")
    val updatedAt : String,
    @SerializedName("sequence")
    val sequence : String
)

data class BidsAndAsks(
    val book : String?,
    val price : String?,
    val amount : String?
)

data class BidsAndAsksList(
    val asks :  ArrayList<BidsAndAsks>,
    val bids :  ArrayList<BidsAndAsks>,
)