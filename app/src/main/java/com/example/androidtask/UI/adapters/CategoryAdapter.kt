package com.example.androidtask.UI.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtask.R
import com.example.androidtask.business.models.HomeModel
import com.example.androidtask.databinding.ItemProductBinding

class CategoryAdapter ():
    ListAdapter<HomeModel, CategoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = DataBindingUtil.inflate<ItemProductBinding>(
            LayoutInflater.from(parent.context), R.layout.item_product, parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it) }
//        holder.itemView.setOnClickListener {
//            getItem(position)?.let { it1 -> onClickListener.onClick(it1) }
//        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<HomeModel>() {
        override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
            return oldItem.id == newItem.id
        }
    }


}

class CategoryViewHolder(private var binding: ItemProductBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(Item: HomeModel) {
        binding.data = Item
        binding.executePendingBindings()
    }
}

