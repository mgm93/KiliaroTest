package com.mgm.kiliaro.data.remote

import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import com.mgm.kiliaro.networking.NetworkResponse

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface Repository {
    suspend fun isOnline(): Boolean
    suspend fun getSharedMedia(): NetworkResponse<ArrayList<ShareMediaResponse>>
    fun getSharedMediaLocal(): ArrayList<ShareMediaResponse>?
    fun clearAllCache()
}