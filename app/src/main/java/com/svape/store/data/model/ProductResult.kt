package com.svape.store.data.model


import com.google.gson.annotations.SerializedName

data class ProductResult(
    @SerializedName("products")
    val products: List<Product>
)