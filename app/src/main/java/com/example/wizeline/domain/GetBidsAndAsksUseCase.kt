package com.example.wizeline.domain

import com.example.wizeline.data.datasource.models.BidsAndAsks
import com.example.wizeline.data.datasource.models.Book
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.data.repository.CurrencyRepository
import com.example.wizeline.utils.orFalse

class GetBidsAndAsksUseCase (
    private val currencyRepository : CurrencyRepository
){
   /* suspend operator fun invoke(book: String):List<BidsAndAsks>{
        return currencyRepository.fetchBidsAndAsks(book)
    }*/
}