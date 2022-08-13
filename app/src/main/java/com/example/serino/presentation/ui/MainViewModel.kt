package com.example.serino.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serino.data.database.ProductDatabaseDao
import com.example.serino.data.model.DomainProduct
import kotlinx.coroutines.launch

class MainViewModel(
    private val productDatabaseDao: ProductDatabaseDao,
) : ViewModel() {

    private val _productList = productDatabaseDao.getAll()
    val productList: LiveData<List<DomainProduct>>
        get() = _productList

    init {
        for (i in 0..50) {
            val item = DomainProduct(
                id = i.toLong(),
                name = "Product $i"
            )
            insertProduct(item)
        }
    }

    fun insertProduct(product: DomainProduct) {
        viewModelScope.launch {
            insert(product)
        }
    }

    private suspend fun insert(product: DomainProduct) {
        productDatabaseDao.insert(product)
    }
}