package com.svape.store.domain.webService

import com.google.gson.GsonBuilder
import com.svape.store.data.model.Categories
import com.svape.store.data.model.ProductResult
import com.svape.store.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebService {

    @GET("products")
    suspend fun getProducts(): ProductResult

    @GET("products/categories")
    suspend fun getCategories(): Response<Categories>
}

object RetrofitClient {

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }

}