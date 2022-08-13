package com.example.serino.data.model

import android.os.Parcelable
import com.example.serino.presentation.util.smartTruncate
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat

@Parcelize
data class Product(
    val id: Long,
    val title: String,
    val description: String,
    val price: Float,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>,
) : Parcelable {

    @IgnoredOnParcel
    val shortDescription = description.smartTruncate(100)

    @IgnoredOnParcel
    val priceString = "$%.2f".format(price)
}