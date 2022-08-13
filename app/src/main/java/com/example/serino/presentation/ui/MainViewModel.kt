package com.example.serino.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.serino.data.model.NetworkProduct
import com.example.serino.data.model.Product
import com.example.serino.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _productList = productRepository.products
    val productList: LiveData<List<Product>>
        get() = _productList

    private val _currentProduct = MutableLiveData<Product>()
    val currentProduct: LiveData<Product>
        get() = _currentProduct

    private val _isError = MutableLiveData<String>()
    val isError: LiveData<String>
        get() = _isError

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
//        clearDatabase()
//        if (_productList.value.isNullOrEmpty()) {
        getProducts()
//        }
    }

    fun setCurrentProduct(product: Product) {
        _currentProduct.value = product
    }

    private fun clearDatabase() {
        viewModelScope.launch {
            productRepository.nukeTable()
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            try {
                productRepository.getProducts()
            } catch (e: Exception) {
                Log.e("MainViewModel", "$e")
                _isError.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    private var currentResult: Flow<PagingData<NetworkProduct>>? = null
    fun getProducts2(): Flow<PagingData<NetworkProduct>> {
        _isLoading.value = false
        val newResult: Flow<PagingData<NetworkProduct>> =
            productRepository.getApiResultStream().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }
}