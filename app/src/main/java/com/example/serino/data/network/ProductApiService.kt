package com.example.serino.data.network

import com.example.serino.data.model.NetworkProductContainer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getProducts(): NetworkProductContainer

    companion object {
        private const val BASE_URL = "https://dummyjson.com/"

        fun create(): ProductApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }
}