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
import com.example.androidtask.databinding.ItemProduct3Binding

class TrendingAdapter  : ListAdapter<DataHomeModel, TrendingViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val binding = DataBindingUtil.inflate<ItemProduct3Binding>(
            LayoutInflater.from(parent.context), R.layout.item_product3, parent, false
        )
        return TrendingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
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

class TrendingViewHolder(private var binding: ItemProduct3Binding) :
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
