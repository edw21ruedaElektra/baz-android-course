package com.example.wizeline

import com.example.wizeline.data.repository.CurrencyRepository
import com.example.wizeline.domain.FilterCurrenciesUseCase
import com.example.wizeline.utils.mockListBooks
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteViewModelUnitTest {
    @RelaxedMockK
    private lateinit var currencyRepository: CurrencyRepository

    lateinit var filterCurrenciesUseCase: FilterCurrenciesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        filterCurrenciesUseCase = FilterCurrenciesUseCase(currencyRepository)
    }

    @Test
    fun `cuando la api devuelve algo, obtiene valores de la api`() = runBlocking {
        //Given
        val myList = mockListBooks()
        coEvery { currencyRepository.fetchAvailableBooks() } returns myList

        //When
        val response = filterCurrenciesUseCase()

        //Then
        coVerify(exactly = 1) { currencyRepository.insertBooks(any()) }
        assert(response == myList)
    }

}