package com.example.serino.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.serino.data.database.ProductDatabaseDao
import com.example.serino.data.model.Product
import com.example.serino.data.model.asDatabaseModel
import com.example.serino.data.model.asDomainModel
import com.example.serino.data.network.ProductApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productDatabaseDao: ProductDatabaseDao,
    private val productApiService: ProductApiService,
) {

    val products: LiveData<List<Product>> = Transformations.map(productDatabaseDao.getAll()) {
        it.asDomainModel()
    }

    suspend fun nukeTable() {
        withContext(Dispatchers.IO) {
            productDatabaseDao.nukeTable()
        }
    }

    suspend fun getProducts(skip: Int = 0) {
        withContext(Dispatchers.IO) {
            val products = productApiService.getProducts(skip)
            productDatabaseDao.insertAll(products.asDatabaseModel())
        }
    }
}