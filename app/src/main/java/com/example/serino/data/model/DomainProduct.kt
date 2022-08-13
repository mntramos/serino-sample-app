package com.example.serino.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "product_database")
data class DomainProduct(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String = "",
): Parcelable

//fun DomainProduct.asDatabaseModel(): DatabaseProduct {
//    return DatabaseProduct(
//        id = id,
//        name = name
//    )
//}
