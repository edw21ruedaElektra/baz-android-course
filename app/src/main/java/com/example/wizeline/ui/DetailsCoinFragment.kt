package com.example.wizeline.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wizeline.R
import com.example.wizeline.databinding.FragmentDetailsCoinBinding
import com.example.wizeline.ui.adapters.ListAsksBidsAdapter
import com.example.wizeline.utils.formatMXN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsCoinFragment : Fragment() {
    private val bitsoVm:BitsoViewModel by activityViewModels()
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
                    tvMinimumPrice.text = resources.getString(R.string.minimum_price_text,bookSelected.minimumPrice.formatMXN())
                    tvMaximumPrice.text = resources.getString(R.string.maximum_price_text,bookSelected.maximumPrice.formatMXN())
                }
                bookSelected.book?.let {
                    bitsoVm.getBidsAndAsks(it)
                    bitsoVm.getTicker(it)
                }
            }
        }
        bitsoVm.ticker.observe(viewLifecycleOwner) { bookSelected ->
            bookSelected?.let {
                bindingView.apply {
                    tvLastPrice.text = resources.getString(R.string.last_price_text,it.last.formatMXN())
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