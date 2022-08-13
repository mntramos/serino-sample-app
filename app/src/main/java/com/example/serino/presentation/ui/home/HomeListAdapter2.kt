package com.example.serino.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.serino.data.model.NetworkProduct
import com.example.serino.databinding.ProductListItem2Binding
import com.example.serino.presentation.binding.DataBindingPresenter

class HomeListAdapter2(
    private val presenter: DataBindingPresenter? = null,
) : PagingDataAdapter<NetworkProduct, HomeListAdapter2.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: ProductListItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NetworkProduct, presenter: DataBindingPresenter?) {
            binding.item = item
            presenter?.let { binding.presenter = it }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductListItem2Binding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item, presenter)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<NetworkProduct>() {
            override fun areItemsTheSame(
                oldItem: NetworkProduct,
                newItem: NetworkProduct,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NetworkProduct,
                newItem: NetworkProduct,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}