package com.example.serino.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseProduct(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String = "",
    val description: String = "",
    val price: Float = 0F,
    val brand: String = "",
    val category: String = "",
    val thumbnail: String = "",
    val images: List<String> = listOf(),
)

fun List<DatabaseProduct>.asDomainModel(): List<Product> {
    return map {
        Product(
            id = it.id,
            title = it.title,
            description = it.description,
            price = it.price,
            brand = it.brand,
            category = it.category,
            thumbnail = it.thumbnail,
            images = it.images
        )
    }
}
