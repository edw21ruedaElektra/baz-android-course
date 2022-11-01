package com.example.wizeline.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wizeline.data.datasource.RemoteDataSourceImpl
import com.example.wizeline.data.repository.CurrencyRepositoryImpl
import com.example.wizeline.data.service.service
import com.example.wizeline.databinding.FragmentDetailsCoinBinding
import com.example.wizeline.ui.adapters.ListAsksBidsAdapter

class DetailsCoinFragment : Fragment() {
    private val bitsoVm by activityViewModels<BitsoViewModel>()
    private lateinit var bindingView: FragmentDetailsCoinBinding
    private var bidsAdapter = ListAsksBidsAdapter()
    private var asksAdapter = ListAsksBidsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingView = FragmentDetailsCoinBinding.inflate(inflater, container, false)
        return bindingView.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        bitsoVm.bidsAndAsks.observe(viewLifecycleOwner) { asksAndBids ->
            if (asksAndBids.asks.isEmpty())
                println("lista vacia asks")
            else {
                asksAdapter.submitList(asksAndBids.asks)
            }
            if (asksAndBids.bids.isEmpty())
                println("lista vacia bids")
            else {
                bidsAdapter.submitList(asksAndBids.bids)
            }
        }

        bitsoVm.bookSelected.observe(viewLifecycleOwner) { bookSelected ->
            if (bookSelected != null) {
                bindingView.apply {
                    tvTitle.text = bookSelected.book
                    tvMinimumPrice.text = "El precio mínimo es: ${bookSelected.minimumPrice}"
                    tvMaximumPrice.text = "El precio máximo es: ${bookSelected.maximumPrice}"
                }
                bitsoVm.getBidsAndAsks(bookSelected.book!!)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun bind(){
        bindingView.apply {
            rvListAsks.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = asksAdapter
                itemAnimator = DefaultItemAnimator()
            }
            rvListBids.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = bidsAdapter
                itemAnimator = DefaultItemAnimator()
            }
        }
    }

}