package com.example.serino.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.serino.data.ProductPagingSource
import com.example.serino.data.database.ProductDatabaseDao
import com.example.serino.data.model.NetworkProduct
import com.example.serino.data.model.Product
import com.example.serino.data.model.asDatabaseModel
import com.example.serino.data.model.asDomainModel
import com.example.serino.data.network.ProductApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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

    suspend fun getProducts() {
        withContext(Dispatchers.IO) {
            val products = productApiService.getProducts(5, 10)
            productDatabaseDao.insertAll(products.asDatabaseModel())
        }
    }

    fun getApiResultStream(): Flow<PagingData<NetworkProduct>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { ProductPagingSource(productApiService) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}