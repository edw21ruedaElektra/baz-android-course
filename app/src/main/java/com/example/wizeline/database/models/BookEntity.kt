package com.example.wizeline.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "books",
)
data class BookEntity(
    @PrimaryKey
    @ColumnInfo(name="book")
    val book : String,
    val minimumAmount : String,
    val maximumAmount : String,
    val minimumPrice : String,
    val maximumPrice : String,
    val minimumValue : String,
    val maximumValue : String
)