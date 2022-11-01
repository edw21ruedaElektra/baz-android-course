package com.example.wizeline.ui

import androidx.lifecycle.*
import com.example.wizeline.data.datasource.models.Book
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.domain.FilterCurrenciesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.wizeline.data.datasource.models.BidsAndAsksList
import com.example.wizeline.data.repository.CurrencyRepository

class BitsoViewModel(
    private val repository: CurrencyRepository,
    private val filterCurrenciesUseCase: FilterCurrenciesUseCase
    ) : ViewModel(){

    private val _availableBooks = MutableStateFlow(arrayListOf<Book>())
    val availableBooks = _availableBooks.asStateFlow()
    var _availableBooksL = MutableLiveData<List<BookInfoEntity>>()
    var availableBooksL: LiveData<List<BookInfoEntity>> = _availableBooksL
    var _bidsAndAsks = MutableLiveData<BidsAndAsksList>()
    var bidsAndAsks: LiveData<BidsAndAsksList> = _bidsAndAsks
    var _bookSelected = MutableLiveData<BookInfoEntity>()
    var bookSelected: LiveData<BookInfoEntity> = _bookSelected

    fun getAvailableBooks() {
        viewModelScope.launch {
            val books = filterCurrenciesUseCase.invoke()
            books.forEach {
            }
            _availableBooksL.value = books
        }
    }
    fun getBidsAndAsks(book:String) {
        viewModelScope.launch {
            val books = repository.getAsksAndBids(book)
            books.asks.forEach {
                println("los asks son $it")
            }
            books.bids.forEach {
                println("los bids son $it")
            }
            _bidsAndAsks.value = books
        }
    }

    fun saveBookSelected(item : BookInfoEntity){
        _bookSelected.value = item
    }
}
class BitsoViewModelFactory(
    private val repository: CurrencyRepository,
    private val filterCurrenciesUseCase: FilterCurrenciesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CurrencyRepository::class.java,FilterCurrenciesUseCase::class.java
        ).newInstance(repository,filterCurrenciesUseCase)
    }
}