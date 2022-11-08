package com.example.wizeline.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wizeline.data.datasource.models.BookInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AvailableBooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(characterEntity: BookInfoEntity)

    @Insert
    suspend fun insertBook(characterEntity: List<BookInfoEntity>)

    @Query(
        """
            SELECT * FROM books
        """
    )
    fun getBooks(): Flow<List<BookInfoEntity>>

}