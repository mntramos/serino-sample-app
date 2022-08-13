package com.example.serino.presentation.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.serino.data.model.DomainProduct
import com.example.serino.databinding.ProductListItemBinding
import com.example.serino.presentation.binding.DataBindingPresenter

class ProductListAdapter(
    private val presenter: DataBindingPresenter? = null
) : ListAdapter<DomainProduct, ProductListAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainProduct, presenter: DataBindingPresenter?) {
            binding.item = item
            presenter?.let { binding.presenter = it }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductListItemBinding.inflate(layoutInflater)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, presenter)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DomainProduct>() {
            override fun areItemsTheSame(oldItem: DomainProduct, newItem: DomainProduct): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DomainProduct, newItem: DomainProduct): Boolean {
                return oldItem == newItem
            }

        }
    }
}