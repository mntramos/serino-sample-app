package com.example.serino.presentation.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.serino.R
import com.example.serino.presentation.binding.setImageUrl

class ProductImageListAdapter : RecyclerView.Adapter<ProductImageListAdapter.ViewHolder>() {

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.iv_image)

        fun bind(item: String) {
            image.setImageUrl(item)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.product_image_list_item, parent, false)

                return ViewHolder(view)
            }
        }
    }

    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size
}

//class ProductImageListAdapter :
//    ListAdapter<String, ProductImageListAdapter.ViewHolder>(DiffCallback) {
//
//    class ViewHolder(private val binding: ProductImageListItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(url: String) {
//            binding.url = url
//            binding.executePendingBindings()
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = ProductImageListItemBinding.inflate(layoutInflater)
//
//                return ViewHolder(binding)
//            }
//        }
//    }
//
//    companion object {
//        private val DiffCallback = object : DiffUtil.ItemCallback<String>() {
//            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
//                return oldItem.hashCode() == newItem.hashCode()
//            }
//
//            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder.from(parent)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.bind(item)
//    }
//}