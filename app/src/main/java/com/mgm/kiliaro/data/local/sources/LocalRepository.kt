package com.mgm.kiliaro.data.local.sources

import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface LocalRepository {

    fun clearAllSharedPrefs()
    suspend fun saveSharedMedia(userDetail: ArrayList<ShareMediaResponse>)
    fun getSharedMedia(): ArrayList<ShareMediaResponse>?
}