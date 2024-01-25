package com.example.androidtask.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtask.R
import com.example.androidtask.business.models.DataHomeModel
import com.example.androidtask.databinding.ItemProduct2Binding
import com.example.androidtask.databinding.ItemProductBinding

class PopularAdapter  : ListAdapter<DataHomeModel, PopularViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = DataBindingUtil.inflate<ItemProduct2Binding>(
            LayoutInflater.from(parent.context), R.layout.item_product2, parent, false
        )
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<DataHomeModel>() {
        override fun areItemsTheSame(oldItem: DataHomeModel, newItem: DataHomeModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataHomeModel, newItem: DataHomeModel): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

class PopularViewHolder(private var binding: ItemProduct2Binding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DataHomeModel) {
        binding.data = item
// Load image using Glide as a test
        Glide.with(binding.root.context)
            .load(item.image)
            .into(binding.mainImage)
        binding.executePendingBindings()
    }
}
