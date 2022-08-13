package com.example.serino.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
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

    init {
        getProductsFromNetwork()
    }

    private fun getProductsFromNetwork() {
        viewModelScope.launch {
            try {
                productRepository.getProductsFromNetwork()
            } catch (e: Exception) {
                Log.e("MainViewModel", "$e")
            }
        }
    }
}