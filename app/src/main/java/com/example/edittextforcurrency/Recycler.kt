package com.example.edittextforcurrency

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.edittextforcurrency.databinding.CountryItemBinding

class Recycler() : ListAdapter<PayoutCountriesResponse, Recycler.MyHolder>(TaskDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        currentList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount() = currentList.size

    fun setList(list: ArrayList<PayoutCountriesResponse?>) {
        submitList(list)
    }

    inner class MyHolder(private val binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PayoutCountriesResponse) {
            binding.country.text = item.country
        }
    }

    class TaskDiffCallBack : DiffUtil.ItemCallback<PayoutCountriesResponse>() {

        override fun areItemsTheSame(
            oldItem: PayoutCountriesResponse,
            newItem: PayoutCountriesResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PayoutCountriesResponse,
            newItem: PayoutCountriesResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface CountryClickListener {
        fun onCountryClick(model: PayoutCountriesResponse)
        fun onLoadCompleted()
    }


}
data class PayoutCountriesResponse (val country:String)

