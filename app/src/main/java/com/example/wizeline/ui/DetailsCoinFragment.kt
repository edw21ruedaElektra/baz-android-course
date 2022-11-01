package com.example.wizeline.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wizeline.R
import com.example.wizeline.databinding.FragmentDetailsCoinBinding
import com.example.wizeline.ui.adapters.ListAsksBidsAdapter

class DetailsCoinFragment : Fragment() {
    private val bitsoVm by navGraphViewModels<BitsoViewModel>(R.id.nav_graph)
    private lateinit var bindingView: FragmentDetailsCoinBinding
    private var bidsAdapter = ListAsksBidsAdapter()
    private var asksAdapter = ListAsksBidsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingView = FragmentDetailsCoinBinding.inflate(inflater, container, false)
        return bindingView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        bitsoVm.bidsAndAsks.observe(viewLifecycleOwner) { asksAndBids ->
            asksAdapter.submitList(asksAndBids.asks)
            bidsAdapter.submitList(asksAndBids.bids)
        }

        bitsoVm.bookSelected.observe(viewLifecycleOwner) { bookSelected ->
            bookSelected?.let {
                bindingView.apply {
                    tvTitle.text = bookSelected.book
                    tvMinimumPrice.text = resources.getString(R.string.minimum_price_text,bookSelected.minimumPrice)
                    tvMaximumPrice.text = resources.getString(R.string.maximum_price_text,bookSelected.maximumPrice)
                }
                bookSelected.book?.let {
                    bitsoVm.getBidsAndAsks(it)
                }
            }
        }

    }
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