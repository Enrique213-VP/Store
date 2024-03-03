package com.svape.store.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.svape.store.domain.repository.ProductsRepository
import com.svape.store.domain.usecase.ProductRepositoryImp
import com.svape.store.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel (
    private val repo: ProductsRepository
) : ViewModel() {

    fun fetchMainScreenProducts() = liveData(Dispatchers.IO) {
        emit(ResponseStatus.Loading())
        try {
            emit(ResponseStatus.Success(repo.getProducts()))
        } catch (e: Exception) {
            emit(ResponseStatus.Failure(e))
        }
    }
}

class ProductViewModelFactory (private val repo: ProductsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ProductsRepository::class.java).newInstance(repo)
    }
}