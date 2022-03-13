package com.mgm.kiliaro.networking

import com.mgm.kiliaro.BuildConfig
import com.mgm.kiliaro.data.local.sources.LocalRepository
import com.mgm.kiliaro.data.remote.api.Api
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val localRepository: LocalRepository,
    gsonConverterFactory: GsonConverterFactory,
    httpInterceptor: HttpInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : Authenticator {

    private var api: Api

    init {
        api = provideApi(
            provideRetrofit(
                provideOkHttpClient(
                    loggingInterceptor,
                    httpInterceptor
                ),
                gsonConverterFactory
            )
        )
    }

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_API)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    private fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        httpInterceptor: HttpInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private fun provideApi(
        retrofit: Retrofit
    ): Api {
        return retrofit.create(Api::class.java)
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        TODO("Not yet implemented")
    }
}