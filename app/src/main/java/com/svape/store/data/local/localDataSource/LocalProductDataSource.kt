package com.svape.store.data.local.localDataSource

import com.svape.store.data.local.dao.ProductDao
import com.svape.store.data.local.entities.ProductEntity
import com.svape.store.data.model.ProductResult
import com.svape.store.utils.toProductList

class LocalProductDataSource (private val productDao: ProductDao) {

    //changes request Dao if need only product or for rating
    suspend fun getAllProducts(): ProductResult {
        return productDao.getProductByRating().toProductList()
    }

    suspend fun saveProduct(product: ProductEntity) {
        productDao.insertProduct(product)
    }
}