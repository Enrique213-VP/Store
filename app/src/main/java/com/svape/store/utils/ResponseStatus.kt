package com.svape.store.utils

// <T> Any date
sealed class ResponseStatus<out T> {
    class Loading<out T> : ResponseStatus<T>()
    data class Success<out T>(val data: T) : ResponseStatus<T>()
    data class Failure(val exception: Exception) : ResponseStatus<Nothing>()
}