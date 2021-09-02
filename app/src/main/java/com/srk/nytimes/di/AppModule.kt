package com.srk.nytimes.di

import com.srk.nytimes.BuildConfig
import com.srk.nytimes.data.remote.APIService
import com.srk.nytimes.data.remote.RemoteDataSource
import com.srk.nytimes.data.repository.BaseRepository
import com.srk.nytimes.data.repository.Repository
import com.srk.nytimes.utils.networkadapter.NetworkResponseAdapterFactory
import com.srk.nytimes.utils.RestConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Sujith RK on 30,August,2021.
 * Copyright (c) 2021 sujithrk89@gmail.com. All rights reserved.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .disableHtmlEscaping()
        .create()

    @Singleton
    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideInterceptor() = Interceptor { chain ->
        val newRequest = chain.request()
            .newBuilder()
            .addHeader(RestConfig.CONTENT_TYPE_KEY, RestConfig.CONTENT_TYPE_VALUE)
            .build()

        chain.proceed(newRequest)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ) = if (BuildConfig.DEBUG) {
        OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(RestConfig.READ_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(RestConfig.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .build()
    } else {
        OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .connectTimeout(RestConfig.READ_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(RestConfig.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: APIService) = RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource) = Repository(remoteDataSource) as BaseRepository
}