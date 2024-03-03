package com.svape.store.di

import android.content.Context
import androidx.room.Room
import com.svape.store.data.local.ProductDatabase
import com.svape.store.data.local.dao.ProductDao
import com.svape.store.data.remote.RemoteProductDataSource
import com.svape.store.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InjectionModule {

    private var INSTANCE: ProductDatabase? = null

    //Injection retrofit

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideProductItemsApi(retrofit: Retrofit): RemoteProductDataSource {
        return retrofit.create(RemoteProductDataSource::class.java)
    }


    //Room

    @Singleton
    @Provides
    fun dataBaseSource(@ApplicationContext context: Context): ProductDatabase {
        INSTANCE = INSTANCE ?: Room.databaseBuilder(context, ProductDatabase::class.java, "product_table")
            .fallbackToDestructiveMigration()
            .build()

        return INSTANCE!!
    }

    @Singleton
    @Provides
    fun productDao(database: ProductDatabase): ProductDao = database.productDao()
}