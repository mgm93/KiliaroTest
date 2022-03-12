package com.mgm.kiliaro.data.remote.sources

import com.google.gson.Gson
import com.mgm.kiliaro.data.remote.models.error.ErrorResponse
import com.mgm.kiliaro.networking.NetworkResponse
import retrofit2.Response

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
open class RemoteRepositoryWrapper {
    inline fun <reified T : Any> networkResponseOf(
        nullResponse: NullResponse,
        service: () -> Response<T>
    ): NetworkResponse<T> {
        return try {
            val response = service()
            if (response.isSuccessful) {
                when (nullResponse) {
                    NullResponse.IgnoreNullResponse -> {
                        NetworkResponse.Success(response.body())
                    }
                    NullResponse.CatchNullResponseError -> {
                        val result = response.body()
                        if (result == null) {
                            NetworkResponse.GenericError(
                                ErrorResponse(
                                    http_code = response.code(),
                                    error = "Null response!"
                                )
                            )
                        } else {
                            NetworkResponse.Success(result)
                        }
                    }
                }
            } else {
                try {
                    val gSon = Gson()
                    val typedValue = gSon.fromJson(
                        response.errorBody()?.string(),
                        ErrorResponse::class.java
                    )
                    NetworkResponse.GenericError(
                        ErrorResponse(
                            response.code(),
                            typedValue.error,
                            typedValue.error_code,
                            typedValue.context
                        )
                    )
                } catch (e: Exception) {
                    NetworkResponse.GenericError(
                        ErrorResponse(
                            http_code = response.code(),
                            error = response.message()
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            NetworkResponse.NetworkError
        }
    }
}

sealed class NullResponse {
    object IgnoreNullResponse : NullResponse()
    object CatchNullResponseError : NullResponse()
}