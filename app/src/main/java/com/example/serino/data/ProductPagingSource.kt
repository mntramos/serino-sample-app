package com.example.serino.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.serino.data.model.NetworkProduct
import com.example.serino.data.network.ProductApiService

private const val PRODUCT_STARTING_PAGE_INDEX = 1

class ProductPagingSource(
    private val productApiService: ProductApiService,
) : PagingSource<Int, NetworkProduct>() {
    override fun getRefreshKey(state: PagingState<Int, NetworkProduct>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkProduct> {
        val page = params.key ?: PRODUCT_STARTING_PAGE_INDEX
        return try {
            val response = productApiService.getProducts(5, 10)
            val products = response.products
            LoadResult.Page(
                data = products,
                prevKey = if (page == PRODUCT_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.total) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}