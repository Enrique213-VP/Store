package com.svape.store.domain.usecase

import com.svape.store.data.local.localDataSource.LocalProductDataSource
import com.svape.store.data.model.ProductResult
import com.svape.store.data.remote.RemoteProductDataSource
import com.svape.store.domain.repository.ProductsRepository
import com.svape.store.utils.InternetCheck
import com.svape.store.utils.toProductEntity
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val productApi: RemoteProductDataSource,
    private val productLocal: LocalProductDataSource
) : ProductsRepository {

    override suspend fun getProducts(): ProductResult {
        return if (InternetCheck.isNetworkAvailable()) {
            productApi.getProduct().products.forEach { product ->
                productLocal.saveProduct(product.toProductEntity())
            }
            productLocal.getAllProducts()
        } else {
            productLocal.getAllProducts()
        }


    }
}