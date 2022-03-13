package com.mgm.kiliaro.networking

import com.mgm.kiliaro.data.local.sources.LocalRepository
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject

class HttpInterceptor @Inject constructor(
    private val localRepository: LocalRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestBuilder = request.newBuilder()

        requestBuilder.removeHeader("Exposed")



        if (request.header("Monolingual") == null) {
            val url = request.url.newBuilder().addQueryParameter(
                "locale",
                Locale.getDefault().language
            ).build()
            requestBuilder.url(url)
        } else {
            requestBuilder.removeHeader("Monolingual")
        }

        val newRequest = requestBuilder.build()

        return try {
            chain.proceed(newRequest)
        } catch (e: Exception) {
            throw IOException(e.message)
        }
    }
}
