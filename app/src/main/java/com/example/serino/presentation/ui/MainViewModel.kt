package com.example.serino.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serino.data.model.Product
import com.example.serino.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
        getProducts()
    }

    fun setCurrentProduct(product: Product) {
        _currentProduct.value = product
    }

    private fun clearDatabase() {
        viewModelScope.launch {
            productRepository.nukeTable()
        }
    }

    fun getProducts(skip: Int = 0) {
        viewModelScope.launch {
            try {
                productRepository.getProducts(skip)
            } catch (e: Exception) {
                Log.e("MainViewModel", "$e")
                _isError.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}