package com.leaf.data.di

import com.leaf.data.RemoteDataSource
import com.leaf.data.RemoteDataSourceImpl
import com.leaf.data.service.WeatherService
import com.leaf.utils.BuildConfigProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun providesWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        weatherService: WeatherService,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(weatherService)
    }
}