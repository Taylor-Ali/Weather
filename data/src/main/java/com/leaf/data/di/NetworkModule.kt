package com.leaf.data.di

import android.content.Context
import com.leaf.data.R
import com.leaf.utils.BuildConfigProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @Named("HttpConnectTimeout")
    fun providesHttpConnectTimeout(@ApplicationContext applicationContext: Context): Int {
        return applicationContext.resources
            .getInteger(R.integer.http_connect_timeout_in_seconds)
    }

    @Provides
    @Singleton
    @Named("HttpReadTimeout")
    fun providesHttpReadTimeout(@ApplicationContext applicationContext: Context): Int {
        return applicationContext.resources
            .getInteger(R.integer.http_read_timeout_in_seconds)
    }

    @Provides
    @Singleton
    @Named("HttpWriteTimeout")
    fun providesHttpWriteTimeout(@ApplicationContext applicationContext: Context): Int {
        return applicationContext.resources
            .getInteger(R.integer.http_write_timeout_in_seconds)
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }


    @Provides
    @Singleton
    fun providesQueryParameterInterceptor(): QueryParameterInterceptor {
        return QueryParameterInterceptor
    }

    @Provides
    @Singleton
    fun providesHttpClient(
        @ApplicationContext applicationContext: Context,
        httpLoggingInterceptor: Interceptor,
        queryParameterInterceptor: QueryParameterInterceptor,
        @Named("HttpConnectTimeout") connectTimeout: Int,
        @Named("HttpReadTimeout") readTimeout: Int,
        @Named("HttpWriteTimeout") writeTimeout: Int
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(readTimeout.toLong(), TimeUnit.SECONDS)
            .writeTimeout(writeTimeout.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(queryParameterInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}