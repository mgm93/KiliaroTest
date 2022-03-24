package com.mgm.kiliaro.data.local.sources

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import com.mgm.kiliaro.generals.utils.SHARED_MEDIA
import com.mgm.kiliaro.generals.utils.SharedPreferenceUtil
import java.lang.reflect.Type
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val sharedPreferenceUtil: SharedPreferenceUtil
) : LocalRepository {

    override fun clearAllCache() {
        sharedPreferenceUtil.clearAllCache()
    }

    override suspend fun saveSharedMedia(userDetail: ArrayList<ShareMediaResponse>) {
        sharedPreferenceUtil.saveObject(SHARED_MEDIA, userDetail)
    }

    override fun getSharedMedia(): ArrayList<ShareMediaResponse>? {
        val sharedMedia = sharedPreferenceUtil.getString(SHARED_MEDIA, "")
        if (sharedMedia.isNullOrEmpty()) {
            return null
        }
        val type: Type = object : TypeToken<java.util.ArrayList<ShareMediaResponse?>?>() {}.type
        return Gson().fromJson(sharedMedia, type)
    }
}