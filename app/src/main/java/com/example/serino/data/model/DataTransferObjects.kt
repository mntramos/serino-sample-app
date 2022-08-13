package com.example.serino.data.model

data class NetworkProductContainer(val products: List<NetworkProduct>)

data class NetworkProduct(
    val id: Long,
    val title: String,
    val description: String,
    val price: Float,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>,
)

fun NetworkProductContainer.asDatabaseModel(): List<DatabaseProduct> {
    return products.map {
        DatabaseProduct(
            id = it.id,
            title = it.title
        )
    }
}
