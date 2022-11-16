package com.example.wizeline.data.repository

import com.example.wizeline.data.datasource.RemoteDataSource
import com.example.wizeline.data.datasource.models.BidsAndAsksList
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.data.datasource.models.TickerEntity
import com.example.wizeline.data.datasource.models.WifiConnected
import com.example.wizeline.database.dao.AvailableBooksDao
import com.example.wizeline.database.models.BookEntity
import com.example.wizeline.utils.toBidsAndAsks
import com.example.wizeline.utils.toBooks
import com.example.wizeline.utils.toTickerEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CurrencyRepositoryImpl  @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val availableBooksDao: AvailableBooksDao,
    private val networkManager: WifiConnected
    ): CurrencyRepository{
    override suspend fun fetchAvailableBooks(): List<BookInfoEntity> {
        return if (networkManager.connected) {
            val books = dataSource.getAvailableBooks().toBooks()
            val entities = books.map { entity ->
                BookEntity(
                    book = entity.book,
                    minimumAmount = entity.minimumAmount,
                    minimumPrice = entity.minimumPrice,
                    minimumValue = entity.minimumValue,
                    maximumAmount = entity.maximumAmount,
                    maximumPrice = entity.maximumPrice,
                    maximumValue = entity.maximumValue
                )
            }
            insertBooks(entities)
            return books
        }
        else
            availableBooksDao.getBooks()
    }

    override suspend fun getAsksAndBids(book: String): BidsAndAsksList {
        return dataSource.getAsksAndBids(book).toBidsAndAsks()
    }

    override suspend fun getTicker(book: String): TickerEntity {
        return dataSource.getTickers(book).toTickerEntity()
    }

    override fun getTickerRX(book: String): Single<TickerEntity> {
        return dataSource.getTickersRX(book).toTickerEntity()
    }

    override suspend fun insertBooks(books: List<BookEntity>) {
        availableBooksDao.insertBook(books)
    }

}