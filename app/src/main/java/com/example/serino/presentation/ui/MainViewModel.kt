package com.example.serino.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.serino.data.database.ProductDatabaseDao
import com.example.serino.data.model.DomainProduct
import com.example.serino.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val productDatabaseDao: ProductDatabaseDao,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productList = productRepository.getProducts().asLiveData()
    val productList: LiveData<List<DomainProduct>>
        get() = _productList

    init {
        for (i in 0..50) {
            val item = DomainProduct(
                id = i.toLong(),
                title = "Product $i"
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
        productRepository.insert(product)
    }
}