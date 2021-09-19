package com.project.rickmortycaseapp.utils

import com.project.rickmortycaseapp.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun getBaseUrl() = Credentials.BASE_URL

    @Provides
    @Singleton
    fun getRetrofitInstance(BASE_URL:String):
            ApiService = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ApiService::class.java)

}