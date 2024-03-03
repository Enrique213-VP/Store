package com.svape.store.domain.repository

import com.svape.store.data.model.ProductResult

interface ProductsRepository {

    suspend fun getProducts() : ProductResult

}