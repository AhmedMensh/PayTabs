package com.android.paytabs.models

sealed class DataResult<out T> {
    data class Success<T>(val content: T?) : DataResult<T>()
    data class Error<T>(val exception: Exception) : DataResult<T>()
}
