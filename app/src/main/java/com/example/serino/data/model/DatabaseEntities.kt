package com.example.serino.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseProduct(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String = "",
)

fun List<DatabaseProduct>.asDomainModel(): List<Product> {
    return map {
        Product(
            id = it.id,
            title = it.title
        )
    }
}
