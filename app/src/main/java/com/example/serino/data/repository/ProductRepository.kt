package com.example.serino.data.repository

import androidx.lifecycle.LiveData
import com.example.serino.data.database.ProductDatabaseDao
import com.example.serino.data.model.DomainProduct
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(private val productDatabaseDao: ProductDatabaseDao) {

//    val products: LiveData<List<DomainProduct>> = productDatabaseDao.getAll()

    fun getProducts() = productDatabaseDao.getAll()

    suspend fun insert(product: DomainProduct) {
        productDatabaseDao.insert(product)
    }
}