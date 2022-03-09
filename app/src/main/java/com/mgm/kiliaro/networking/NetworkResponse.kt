package com.mgm.kiliaro.networking

import com.mgm.kiliaro.data.remote.models.error.ErrorResponse

sealed class NetworkResponse<out T> {
    data class Success<out T>(val rawResponse: T? = null) : NetworkResponse<T>()
    data class GenericError(val error: ErrorResponse) : NetworkResponse<Nothing>()
    object NetworkError : NetworkResponse<Nothing>()
}