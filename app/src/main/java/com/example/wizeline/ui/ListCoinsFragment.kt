package com.example.wizeline.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wizeline.R
import com.example.wizeline.data.datasource.RemoteDataSource
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.data.repository.CurrencyRepository
import com.example.wizeline.data.service
import com.example.wizeline.databinding.FragmentListCoinsBinding
import com.example.wizeline.domain.FilterCurrenciesUseCase
import com.example.wizeline.ui.adapters.ListCoinsAdapter

class ListCoinsFragment : Fragment() {
    private val bitsoVm by viewModels<BitsoViewModel> {
        BitsoViewModelFactory(
            FilterCurrenciesUseCase(
            CurrencyRepository(
                RemoteDataSource(
                    service
                )
            )
        ))
    }
    //NAVGRAV

    private lateinit var bindingView: FragmentListCoinsBinding
    private var coinsAdapter = ListCoinsAdapter{
        goTo(it)
    }

    private fun goTo(book : BookInfoEntity){
        bitsoVm.itemSelected = book
        println("El book ")

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingView = FragmentListCoinsBinding.inflate(inflater, container, false).also {
        }
        return bindingView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bitsoVm.getAvailableBooks()
        bindingView.rvListCoins.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = coinsAdapter
            itemAnimator = DefaultItemAnimator()
        }
        bitsoVm.availableBooksL.observe(viewLifecycleOwner) { availableBooks ->
            if (availableBooks.isEmpty())
                println("lista vacia")
            else {
                coinsAdapter.submitList(availableBooks)
            }
        }

    }
}