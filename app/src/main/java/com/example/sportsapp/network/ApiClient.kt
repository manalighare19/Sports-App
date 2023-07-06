package com.example.sportsapp.network

import com.example.sportsapp.BuildConfig.SPORTS_DB_API_KEY
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/$SPORTS_DB_API_KEY/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}