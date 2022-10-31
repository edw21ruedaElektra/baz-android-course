package com.example.wizeline.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.wizeline.R
import com.example.wizeline.data.datasource.models.Book
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.data.repository.CurrencyRepository
import com.example.wizeline.data.service
import com.example.wizeline.domain.FilterCurrenciesUseCase
import com.example.wizeline.utils.OnClickBack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BitsoViewModel(
    private val filterCurrenciesUseCase: FilterCurrenciesUseCase) : ViewModel(){

    private val _availableBooks = MutableStateFlow(arrayListOf<Book>())
    val availableBooks = _availableBooks.asStateFlow()
    var _availableBooksL = MutableLiveData<List<BookInfoEntity>>()
    var availableBooksL: LiveData<List<BookInfoEntity>> = _availableBooksL
    var itemSelected = BookInfoEntity("","","","","","","")

    fun getAvailableBooks() {
        viewModelScope.launch {
            val books = filterCurrenciesUseCase()
            books.forEach {
            }
            _availableBooksL.value = books
        }
    }

    fun mockData(){

    }
}
class BitsoViewModelFactory(private val useCase: FilterCurrenciesUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FilterCurrenciesUseCase::class.java).newInstance(useCase)
    }
}