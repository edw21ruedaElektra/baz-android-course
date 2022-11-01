package com.example.wizeline.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wizeline.data.datasource.models.BidsAndAsks
import com.example.wizeline.databinding.ItemAsksBidsBinding

class ListAsksBidsAdapter() :
    ListAdapter<BidsAndAsks, ListAsksBidsAdapter.ViewHolder>(compare) {

    companion object{
        val compare = object: DiffUtil.ItemCallback<BidsAndAsks>(){
            override fun areItemsTheSame(oldItem: BidsAndAsks, newItem: BidsAndAsks): Boolean {
                return oldItem.book == newItem.book
            }

            override fun areContentsTheSame(oldItem: BidsAndAsks, newItem: BidsAndAsks): Boolean {
                return oldItem == newItem
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemAsksBidsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    inner class ViewHolder(private val itemBinding: ItemAsksBidsBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItem(item: BidsAndAsks) = with(itemBinding) {
            tvBook.text = "Book: ${item.book}"
            tvPrice.text = "Price: ${item.price}"
            tvAmount.text = "Amount: ${item.amount}"
        }
    }

}