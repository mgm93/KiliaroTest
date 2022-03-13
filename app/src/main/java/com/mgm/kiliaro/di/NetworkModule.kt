package com.mgm.kiliaro.di

import com.google.gson.GsonBuilder
import com.mgm.kiliaro.BuildConfig
import com.mgm.kiliaro.data.local.sources.LocalRepository
import com.mgm.kiliaro.data.remote.api.Api
import com.mgm.kiliaro.networking.AuthInterceptor
import com.mgm.kiliaro.networking.HttpInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Majid-Golmoradi on 3/9/2022.
 * Email: golmoradi.majid@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        scalarsConverterFactory: ScalarsConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_API)
            .addConverterFactory(scalarsConverterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        httpInterceptor: HttpInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .authenticator(authInterceptor)
            .addInterceptor(httpInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Singleton
    @Provides
    fun provideHttpInterceptor(
        localRepository: LocalRepository
    ): HttpInterceptor {
        return HttpInterceptor(localRepository)
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(
        localRepository: LocalRepository,
        gsonConverterFactory: GsonConverterFactory,
        httpInterceptor: HttpInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): AuthInterceptor {
        return AuthInterceptor(
            localRepository,
            gsonConverterFactory,
            httpInterceptor,
            loggingInterceptor
        )
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideScalerConverterFactory(): ScalarsConverterFactory {
        return ScalarsConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideApi(
        retrofit: Retrofit
    ): Api {
        return retrofit.create(Api::class.java)
    }
}