package com.example.wizeline.ui

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.wizeline.BadApplication
import com.example.wizeline.R
import com.example.wizeline.data.datasource.models.Book
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.domain.FilterCurrenciesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import com.example.wizeline.data.datasource.models.BidsAndAsks
import com.example.wizeline.data.datasource.models.BidsAndAsksList
import com.example.wizeline.data.repository.CurrencyRepository
import com.example.wizeline.domain.GetBidsAndAsksUseCase

class BitsoViewModel(
    private val repository: CurrencyRepository
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
            val books = repository.fetchAvailableBooks()
            books.forEach {
            }
            _availableBooksL.value = books
        }
    }
    fun getBidsAndAsks(book:String) {
        viewModelScope.launch {
            val books = repository.getAsksAndBids("btc_mxn")
            books.asks.forEach {
                println("los asks son $it")
            }
            books.bids.forEach {
                println("los bids son $it")
            }
            _bidsAndAsks.value = books
        }
    }

    fun goToDetail(fragment: Fragment,item : BookInfoEntity){
        _bookSelected.value = item
        fragment.findNavController().navigate(R.id.action_listCoinsFragment_to_detailsCoinFragment)
    }
}
class BitsoViewModelFactory(
    private val repository: CurrencyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CurrencyRepository::class.java).newInstance(repository)
    }
}