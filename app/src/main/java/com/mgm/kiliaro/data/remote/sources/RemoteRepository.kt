package com.mgm.kiliaro.data.remote.sources

import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import com.mgm.kiliaro.networking.NetworkResponse

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface RemoteRepository {
    suspend fun getSharedMedia(): NetworkResponse<ArrayList<ShareMediaResponse>>
}