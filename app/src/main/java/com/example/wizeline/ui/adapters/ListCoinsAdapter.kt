package com.example.wizeline.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wizeline.R
import com.example.wizeline.data.datasource.models.BookInfoEntity
import com.example.wizeline.databinding.ItemCurrencyBinding

class ListCoinsAdapter(private val action: (item: BookInfoEntity) -> Unit) :
    ListAdapter<BookInfoEntity, ListCoinsAdapter.ViewHolder>(compare) {

    companion object{
        val compare = object: DiffUtil.ItemCallback<BookInfoEntity>(){
            override fun areItemsTheSame(oldItem: BookInfoEntity, newItem: BookInfoEntity): Boolean {
                return oldItem.book == newItem.book
            }

            override fun areContentsTheSame(oldItem: BookInfoEntity, newItem: BookInfoEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    inner class ViewHolder(private val itemBinding: ItemCurrencyBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItem(item: BookInfoEntity) = with(itemBinding) {
            tvItem.text = item.book
            item.book.let { setImageCoin(ivCurrency, it) }
            root.setOnClickListener {
                action(item)
            }
        }
    }

    fun setImageCoin(itemImage:ImageView, typeCurrency : String){
        when (typeCurrency){
            "usd_mxn" -> itemImage.setImageResource(R.drawable.modena_dolar)
            "btc_mxn" -> itemImage.setImageResource(R.drawable.btc_coin)
            "eth_mxn" -> itemImage.setImageResource(R.drawable.eth_coin)
            "xrp_mxn" -> itemImage.setImageResource(R.drawable.xrp_coin)
            "ltc_mxn" -> itemImage.setImageResource(R.drawable.ltc_coin)
            "bch_mxn" -> itemImage.setImageResource(R.drawable.bch_coin)
            "tusd_mxn" -> itemImage.setImageResource(R.drawable.tusd_coin)
            "mana_mxn" -> itemImage.setImageResource(R.drawable.mana_coin)
            "bat_mxn" -> itemImage.setImageResource(R.drawable.bat_coin)
            "dai_mxn" -> itemImage.setImageResource(R.drawable.dai_coin)
            else -> itemImage.setImageResource(R.drawable.coin_generic)
        }

    }

}