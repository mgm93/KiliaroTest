package com.mgm.kiliaro.data.remote.api

import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface Api {

    @GET("shared/djlCbGusTJamg_ca4axEVw/media")
    suspend fun getSharedMedia(
    ): Response<ArrayList<ShareMediaResponse>>
}