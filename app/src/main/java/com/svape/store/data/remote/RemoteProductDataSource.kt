package com.svape.store.data.remote

import com.svape.store.data.model.ProductResult
import com.svape.store.domain.webService.WebService

class RemoteProductDataSource(private val webService: WebService) {

    suspend fun getProduct(): ProductResult {
        return webService.getProducts()
    }
}