package com.svape.store.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.svape.store.data.local.entities.ProductEntity

@Dao
interface ProductDao {


    @Query("SELECT * FROM product_table ORDER BY rating DESC")
    fun getProductByRating(): List<ProductEntity>


    @Query("SELECT * FROM product_table")
    suspend fun getAllProduct(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)


    @Delete
    fun deleteProduct(product: ProductEntity)
}