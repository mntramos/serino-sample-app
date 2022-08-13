package com.example.serino.presentation.binding

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.serino.R

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}

@BindingAdapter("isGone")
fun View.setGone(value: Boolean?) {
    isGone = value ?: false
}