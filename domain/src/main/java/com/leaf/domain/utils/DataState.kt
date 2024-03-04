package com.leaf.domain.utils

sealed class DataState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    class Success<T>(data: T) : DataState<T>(data)
    class Loading<T>(isLoading: Boolean, data: T? = null, errorMessage: String = "") :
        DataState<T>(data, isLoading, errorMessage)

    class Error<T>(message: String, data: T? = null, isLoading: Boolean) :
        DataState<T>(data, isLoading, message)
}