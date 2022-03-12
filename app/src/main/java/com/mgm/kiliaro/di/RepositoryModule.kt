package com.mgm.kiliaro.di

import com.mgm.kiliaro.data.local.sources.LocalRepository
import com.mgm.kiliaro.data.local.sources.LocalRepositoryImpl
import com.mgm.kiliaro.data.remote.Repository
import com.mgm.kiliaro.data.remote.RepositoryImpl
import com.mgm.kiliaro.data.remote.sources.RemoteRepository
import com.mgm.kiliaro.data.remote.sources.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository

    @Singleton
    @Binds
    abstract fun bindRemoteRepository(
        remoteRepositoryImpl: RemoteRepositoryImpl
    ): RemoteRepository

    @Singleton
    @Binds
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}