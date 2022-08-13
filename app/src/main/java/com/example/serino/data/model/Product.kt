package com.example.serino.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Long = 0L,
    val title: String = "",
) : Parcelable