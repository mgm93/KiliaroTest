package com.mgm.kiliaro.data.remote.sources

import com.mgm.kiliaro.data.remote.api.Api
import com.mgm.kiliaro.data.remote.models.response.ShareMediaResponse
import com.mgm.kiliaro.networking.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */
class RemoteRepositoryImpl @Inject constructor(
    private val api: Api
): RemoteRepositoryWrapper(), RemoteRepository {

    override suspend fun isOnline(): Boolean {
        return withContext(Dispatchers.IO) {
            return@withContext checkInternetConnection()
        }
    }

    // TCP/HTTP/DNS (depending on the port, 53=DNS, 80=HTTP, etc.)
    private fun checkInternetConnection(): Boolean {
        return try {
            val timeoutMs = 2500
            val sock = Socket()
            val sockaddr: SocketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(sockaddr, timeoutMs)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }

    override suspend fun getSharedMedia(): NetworkResponse<ArrayList<ShareMediaResponse>> {
        return withContext(Dispatchers.IO){
            return@withContext networkResponseOf(NullResponse.CatchNullResponseError){
                api.getSharedMedia()
            }
        }
    }
}