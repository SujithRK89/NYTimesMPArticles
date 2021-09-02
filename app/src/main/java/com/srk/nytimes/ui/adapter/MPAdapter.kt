package com.srk.nytimes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.srk.nytimes.data.remote.model.MostPopular
import com.srk.nytimes.databinding.ItemMostPopularBinding
import com.srk.nytimes.handler.MPItemHandler

/**
 * Created by Sujith RK on 31,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
class MPAdapter constructor(
    private val listener: MPItemHandler
    ): ListAdapter<MostPopular, MPAdapter.MPViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MPViewHolder {
        val binding = ItemMostPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MPViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MPViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    inner class MPViewHolder(private val binding: ItemMostPopularBinding, private val listener: MPItemHandler): RecyclerView.ViewHolder(binding.root) {

        fun bind(mostPopular: MostPopular) {
            binding.apply {
                this.mostPopular = mostPopular
                this.handler = listener
            }
            binding.executePendingBindings()
        }

    }

    private class DiffCallback : DiffUtil.ItemCallback<MostPopular>() {

        override fun areItemsTheSame(oldItem: MostPopular, newItem: MostPopular) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MostPopular, newItem: MostPopular) =
            oldItem == newItem
    }
}