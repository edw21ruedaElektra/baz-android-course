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
import com.example.wizeline.database.models.BookEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class BitsoViewModel @Inject constructor(
    private val repository: CurrencyRepository,
    private val filterCurrenciesUseCase: FilterCurrenciesUseCase
    ) : ViewModel(){

    private val _availableBooks = MutableStateFlow(emptyList<Book>())
    val availableBooks = _availableBooks.asStateFlow()
    private val _availableBooksL = MutableLiveData<List<BookInfoEntity>>()
    val availableBooksL: LiveData<List<BookInfoEntity>> = _availableBooksL
    private val _bidsAndAsks = MutableLiveData<BidsAndAsksList>()
    val bidsAndAsks: LiveData<BidsAndAsksList> = _bidsAndAsks
    private val _ticker = MutableLiveData<TickerEntity>()
    val ticker: LiveData<TickerEntity> = _ticker
    private val _bookSelected = MutableLiveData<BookInfoEntity>()
    val bookSelected: LiveData<BookInfoEntity> = _bookSelected

    init {
        getAvailableBooks()
    }
    private fun getAvailableBooks() {
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
    fun getTickerRX(book: String){
        repository.getTickerRX(book)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _ticker.postValue(it)
            },{
            })
    }
    fun saveBookSelected(item : BookInfoEntity){
        _bookSelected.value = item
    }
}