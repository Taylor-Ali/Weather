package com.leaf.repository.di

import com.leaf.cache.LocalDataSource
import com.leaf.data.RemoteDataSource
import com.leaf.repository.PlaceRepository
import com.leaf.repository.PlaceRepositoryImpl
import com.leaf.repository.WeatherForecastRepository
import com.leaf.repository.WeatherForecastRepositoryImpl
import com.leaf.repository.mapper.CityEntityMapper
import com.leaf.repository.mapper.CurrentForecastResponseToEntityMapper
import com.leaf.repository.mapper.CurrentWeatherEntityMapper
import com.leaf.repository.mapper.ForecastToWeatherReportMapper
import com.leaf.repository.mapper.WeeklyForecastEntityMapper
import com.leaf.repository.mapper.WeeklyForecastResponseToEntityMapper
import com.leaf.repository.mapper.WeeklyWeatherForecastResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesWeatherRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        currentWeatherEntityMapper: CurrentWeatherEntityMapper,
        currentForecastResponseToEntityMapper: CurrentForecastResponseToEntityMapper,
        weeklyForecastResponseToEntityMapper: WeeklyForecastResponseToEntityMapper,
    ): WeatherForecastRepository {
        return WeatherForecastRepositoryImpl(
            remoteDataSource, localDataSource, currentWeatherEntityMapper,
            currentForecastResponseToEntityMapper, weeklyForecastResponseToEntityMapper
        )
    }

    @Provides
    @Singleton
    fun providesCityRepository(
        localDataSource: LocalDataSource,
        cityEntityMapper: CityEntityMapper
    ): PlaceRepository {
        return PlaceRepositoryImpl(localDataSource, cityEntityMapper)
    }


    @Provides
    fun providesCityEntityMapper(
    ): CityEntityMapper {
        return CityEntityMapper
    }

    @Provides
    fun providesCurrentWeatherEntityMapper(
    ): CurrentWeatherEntityMapper {
        return CurrentWeatherEntityMapper
    }

    @Provides
    fun providesForecastToWeatherReportMapper(
    ): ForecastToWeatherReportMapper {
        return ForecastToWeatherReportMapper
    }

    @Provides
    fun providesForecastToWeeklyForecastEntityMapper(
    ): WeeklyForecastEntityMapper {
        return WeeklyForecastEntityMapper
    }

    @Provides
    fun providesWeeklyForecastResponseToEntityMapper(
    ): WeeklyForecastResponseToEntityMapper {
        return WeeklyForecastResponseToEntityMapper
    }

    @Provides
    fun providesWeeklyWeatherForecastResponseMapper(
    ): WeeklyWeatherForecastResponseMapper {
        return WeeklyWeatherForecastResponseMapper
    }

    @Provides
    fun providesCurrentForecastResponseToEntityMapper(
    ): CurrentForecastResponseToEntityMapper {
        return CurrentForecastResponseToEntityMapper
    }
}
