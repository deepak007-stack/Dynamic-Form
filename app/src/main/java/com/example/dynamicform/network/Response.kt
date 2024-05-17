package com.example.dynamicform.network

sealed class Response<T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(val msg: String) : Response<T>()
    data class Loading<T>(val data: T? = null) : Response<T>()
}