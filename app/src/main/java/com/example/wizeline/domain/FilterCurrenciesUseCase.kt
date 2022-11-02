package com.example.wizeline.domain

import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.data.repository.CurrencyRepository
import com.example.wizeline.utils.Constants.CRYPTO_MXN_SUFFIX_VALUE
import com.example.wizeline.utils.orFalse

class FilterCurrenciesUseCase (
        private val currencyRepository : CurrencyRepository
){
        suspend operator fun invoke():List<BookInfoEntity>{
                return currencyRepository.fetchAvailableBooks().filter {
                        it.book?.contains(CRYPTO_MXN_SUFFIX_VALUE).orFalse()
                }
        }
}