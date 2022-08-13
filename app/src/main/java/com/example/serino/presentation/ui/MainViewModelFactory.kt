package com.example.serino.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.serino.data.database.ProductDatabaseDao
import com.example.serino.data.repository.ProductRepository

//class MainViewModelFactory(
////    private val productDatabaseDao: ProductDatabaseDao,
//    private val productRepository: ProductRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return MainViewModel(productRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}