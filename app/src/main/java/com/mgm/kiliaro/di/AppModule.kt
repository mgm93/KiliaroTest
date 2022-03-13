package com.mgm.kiliaro.di

import android.content.Context
import com.mgm.kiliaro.generals.OTPLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOtpLiveData(
        @ApplicationContext appContext: Context
    ): OTPLiveData = OTPLiveData(appContext)


}