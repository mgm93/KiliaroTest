package com.mgm.kiliaro.data.remote.source

import com.mgm.kiliaro.data.remote.api.Api
import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import com.mgm.kiliaro.networking.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
class RemoteRepositoryImpl @Inject constructor(
    private val api: Api
): RemoteRepositoryWrapper(), RemoteRepository {

    override suspend fun getSharedMedia(): NetworkResponse<ArrayList<ShareMediaResponse>> {
        return withContext(Dispatchers.IO){
            return@withContext networkResponseOf(NullResponse.CatchNullResponseError){
                api.getSharedMedia()
            }
        }
    }
}