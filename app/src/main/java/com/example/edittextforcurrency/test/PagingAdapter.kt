package com.example.edittextforcurrency.test

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.edittextforcurrency.databinding.CountryItemBinding
import com.example.edittextforcurrency.model.Data

class PagingAdapters : PagingDataAdapter<Data, PagingAdapters.MyHolder>(TaskDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it) }
    }

    inner class MyHolder(private val binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            binding.country.text = item.name

        }
    }

    class TaskDiffCallBack : DiffUtil.ItemCallback<Data>() {

        override fun areItemsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem == newItem
        }
    }

}
