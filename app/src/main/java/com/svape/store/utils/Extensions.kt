package com.svape.store.utils

import com.svape.store.data.local.entities.ProductEntity
import com.svape.store.data.model.Product
import com.svape.store.data.model.ProductResult

//Transform List
fun List<ProductEntity>.toProductList(): ProductResult {
    val resultList = mutableListOf<Product>()
    this.forEach{ productEntity ->
        resultList.add(productEntity.toProduct())
    }
    return ProductResult(resultList)
}

fun ProductEntity.toProduct(): Product = Product(
    this.brand,
    this.category,
    this.description,
    this.discountPercentage,
    this.id,
    this.price,
    this.rating,
    this.stock,
    this.thumbnail,
    this.title
)

fun Product.toProductEntity(): ProductEntity = ProductEntity(
    this.brand,
    this.category,
    this.description,
    this.discountPercentage,
    this.id,
    this.price,
    this.rating,
    this.stock,
    this.thumbnail,
    this.title
)