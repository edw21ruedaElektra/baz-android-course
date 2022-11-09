package com.example.wizeline.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wizeline.R
import com.example.wizeline.databinding.FragmentListCoinsBinding
import com.example.wizeline.ui.adapters.ListCoinsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCoinsFragment : Fragment() {
    private val bitsoVm:BitsoViewModel by activityViewModels()

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