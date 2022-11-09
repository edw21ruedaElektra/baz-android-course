package com.example.wizeline.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wizeline.database.dao.AvailableBooksDao
import com.example.wizeline.database.models.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class WizelineDatabase: RoomDatabase() {
    abstract fun booksDao(): AvailableBooksDao
}