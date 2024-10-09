package com.example.domain.utils

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val data: T) : ResponseWrapper<T>()
    data class Error(val message: String) : ResponseWrapper<Nothing>()
}
