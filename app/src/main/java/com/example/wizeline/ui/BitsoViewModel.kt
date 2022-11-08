package com.example.wizeline.ui

import androidx.lifecycle.*
import com.example.wizeline.data.datasource.models.Book
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.domain.FilterCurrenciesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.wizeline.data.datasource.models.BidsAndAsksList
import com.example.wizeline.data.datasource.models.TickerEntity
import com.example.wizeline.data.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class BitsoViewModel @Inject constructor(
    private val repository: CurrencyRepository,
    private val filterCurrenciesUseCase: FilterCurrenciesUseCase
    ) : ViewModel(){

    private val _availableBooks = MutableStateFlow(emptyList<Book>())
    val availableBooks = _availableBooks.asStateFlow()
    var _availableBooksL = MutableLiveData<List<BookInfoEntity>>()
    var availableBooksL: LiveData<List<BookInfoEntity>> = _availableBooksL
    var _bidsAndAsks = MutableLiveData<BidsAndAsksList>()
    var bidsAndAsks: LiveData<BidsAndAsksList> = _bidsAndAsks
    var _ticker = MutableLiveData<TickerEntity>()
    var ticker: LiveData<TickerEntity> = _ticker
    var _bookSelected = MutableLiveData<BookInfoEntity>()
    var bookSelected: LiveData<BookInfoEntity> = _bookSelected

    fun getAvailableBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            val books = filterCurrenciesUseCase.invoke()
            _availableBooksL.postValue(books)
        }
    }
    fun getBidsAndAsks(book:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val books = repository.getAsksAndBids(book)
            _bidsAndAsks.postValue(books)
        }
    }

    fun getTicker(book: String){
        viewModelScope.launch(Dispatchers.IO) {
            val bookInfo = repository.getTicker(book)
            _ticker.postValue(bookInfo)
        }
    }
    fun check(){
        println("el book es ${bookSelected.value}")
    }

    fun saveBookSelected(item : BookInfoEntity){
        _bookSelected.value = item
        println("el book es ${bookSelected.value}")
    }
}