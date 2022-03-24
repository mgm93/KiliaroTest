package com.mgm.kiliaro.data.remote

import com.mgm.kiliaro.data.local.sources.LocalRepository
import com.mgm.kiliaro.data.remote.models.error.ErrorResponse
import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import com.mgm.kiliaro.data.remote.sources.RemoteRepository
import com.mgm.kiliaro.networking.NetworkResponse
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
class RepositoryImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
): Repository {

    private fun sendImportantNullResponseError(): NetworkResponse.GenericError {
        return NetworkResponse.GenericError(
            ErrorResponse(
                http_code = 200,
                error = "Null response!"
            )
        )
    }

    override suspend fun isOnline(): Boolean {
        return remoteRepository.isOnline()
    }

    override suspend fun getSharedMedia(): NetworkResponse<ArrayList<ShareMediaResponse>> {
        val result = remoteRepository.getSharedMedia()
        when (result) {
            is NetworkResponse.Success -> {
                if (result.rawResponse == null) {
                    return sendImportantNullResponseError()
                }
                //todo saveSharedMedia
                localRepository.saveSharedMedia(result.rawResponse)
            }
        }
        return result
    }

    override fun getSharedMediaLocal(): ArrayList<ShareMediaResponse>? {
        return localRepository.getSharedMedia()
    }

    override fun clearAllCache() {
        localRepository.clearAllCache()
    }


}