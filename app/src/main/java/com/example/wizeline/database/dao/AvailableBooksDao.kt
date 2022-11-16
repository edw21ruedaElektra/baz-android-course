package com.example.wizeline.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.database.models.BookEntity

@Dao
interface AvailableBooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(booksEntity: BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(booksEntity: List<BookEntity>)

    @Query(
        """
            SELECT * FROM books
        """
    )
    fun getBooks(): List<BookInfoEntity>

}