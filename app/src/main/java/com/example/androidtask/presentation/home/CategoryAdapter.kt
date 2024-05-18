package com.example.androidtask.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtask.R
import com.example.androidtask.data.remote.models.CaregoryItem
import com.example.androidtask.databinding.ItemProductBinding

class CategoryAdapter() :
    ListAdapter<CaregoryItem, CategoryViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = DataBindingUtil.inflate<ItemProductBinding>(
            LayoutInflater.from(parent.context), R.layout.item_product, parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<CaregoryItem>() {
        override fun areItemsTheSame(oldItem: CaregoryItem, newItem: CaregoryItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CaregoryItem, newItem: CaregoryItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

class CategoryViewHolder(private var binding: ItemProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CaregoryItem) {
        binding.data = item
// Load image using Glide as a test
        Glide.with(binding.root.context)
            .load(item.image)
            .into(binding.mainImage)
        binding.executePendingBindings()
    }
}

