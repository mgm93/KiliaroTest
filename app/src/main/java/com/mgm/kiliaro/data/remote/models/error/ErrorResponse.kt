package com.mgm.kiliaro.data.remote.models.error

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
data class ErrorResponse(
    @SerializedName("http_code") val http_code: Int,
    @SerializedName("error") val error: String,
    @SerializedName("error_code") val error_code: Int? = null,
    @SerializedName("context") val context: JsonObject? = null
)
