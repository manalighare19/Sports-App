package com.example.sportsapp.di

import com.example.sportsapp.network.ApiClient
import com.example.sportsapp.network.ApiService
import com.example.sportsapp.repository.PlayerInfoRepository
import com.example.sportsapp.repository.PlayerInfoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRepository(impl: PlayerInfoRepositoryImpl): PlayerInfoRepository {
        return impl
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return ApiClient.buildService(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}