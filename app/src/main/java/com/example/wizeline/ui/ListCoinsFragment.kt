package com.example.wizeline.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wizeline.R
import com.example.wizeline.data.datasource.RemoteDataSourceImpl
import com.example.wizeline.data.repository.CurrencyRepositoryImpl
import com.example.wizeline.data.service.service
import com.example.wizeline.databinding.FragmentListCoinsBinding
import com.example.wizeline.domain.FilterCurrenciesUseCase
import com.example.wizeline.ui.adapters.ListCoinsAdapter

class ListCoinsFragment : Fragment() {
    private val bitsoVm by navGraphViewModels<BitsoViewModel>(R.id.nav_graph){
        BitsoViewModelFactory(
            CurrencyRepositoryImpl(
                RemoteDataSourceImpl(
                    service
                )
            ),
            FilterCurrenciesUseCase(
                CurrencyRepositoryImpl(
                    RemoteDataSourceImpl(
                        service
                    )
                )
            )
        )
    }

    private lateinit var bindingView: FragmentListCoinsBinding
    private var coinsAdapter = ListCoinsAdapter{
        bitsoVm.saveBookSelected(it)
        findNavController().navigate(R.id.action_listCoinsFragment_to_detailsCoinFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingView = FragmentListCoinsBinding.inflate(inflater, container, false)
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
                coinsAdapter.submitList(availableBooks)
        }

    }
}