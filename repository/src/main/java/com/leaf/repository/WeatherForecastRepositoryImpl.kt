package com.leaf.repository

import com.leaf.cache.LocalDataSource
import com.leaf.data.RemoteDataSource
import com.leaf.data.requests.CurrentForecastRequest
import com.leaf.data.requests.WeeklyForecastRequest
import com.leaf.repository.mapper.CurrentForecastResponseToEntityMapper
import com.leaf.repository.mapper.CurrentWeatherEntityMapper
import com.leaf.repository.mapper.WeeklyForecastEntityMapper
import com.leaf.repository.mapper.WeeklyForecastResponseToEntityMapper
import com.leaf.repository.model.ForecastRequest
import com.leaf.repository.model.WeatherReport
import com.leaf.repository.model.WeeklyForecastReport
import com.leaf.repository.utils.Resource
import com.leaf.repository.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class WeatherForecastRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val currentWeatherEntityMapper: CurrentWeatherEntityMapper,
    private val currentForecastResponseToEntityMapper: CurrentForecastResponseToEntityMapper,
    private val weeklyForecastResponseToEntityMapper: WeeklyForecastResponseToEntityMapper,
) : WeatherForecastRepository {
    override suspend fun getCurrentWeatherReport(forecastRequest: ForecastRequest): Flow<Resource<WeatherReport?>> {
        return networkBoundResource(
            query = {
                localDataSource.fetchCurrentWeatherForecast(forecastRequest.cityName.orEmpty())
                    .map {
                        currentWeatherEntityMapper.mapToModel(it)
                    }
            },
            fetch = {
                remoteDataSource.fetchCurrentWeatherForecast(
                    CurrentForecastRequest(
                        latitude = forecastRequest.latitude ?: 0.0,
                        longitude = forecastRequest.longitude ?: 0.0,
                        cityName = forecastRequest.cityName.orEmpty(),
                    )
                )
            },
            saveFetchResult = {
                it.body()?.let { resource ->
                    currentForecastResponseToEntityMapper.mapToModel(resource)
                        ?.let { data ->
                            localDataSource.storeCurrentWeatherForecast(data)
                            localDataSource.cacheCurrentWeatherForecast(isCached = true)
                        }
                }

            }, shouldFetch = {
                shouldFetchFromApi(forecastRequest)
            },
            onFetchFailed = {
                flow<String> { it.message }
            })
    }


    override suspend fun getWeeklyWeatherReport(forecastRequest: ForecastRequest): Flow<Resource<WeeklyForecastReport?>> {
        return networkBoundResource(
            query = {
                localDataSource.fetchWeeklyWeatherForecast(forecastRequest.cityName.orEmpty()).map {
                    WeeklyForecastEntityMapper.mapToModel(it)
                }
            },
            fetch = {
                remoteDataSource.fetchWeeklyForecastResponse(
                    WeeklyForecastRequest(
                        latitude = forecastRequest.latitude ?: 0.0,
                        longitude = forecastRequest.longitude ?: 0.0,
                        cityName = forecastRequest.cityName.orEmpty(),
                    )
                )
            },
            saveFetchResult = {
                it.body()?.let { resource ->
                    weeklyForecastResponseToEntityMapper.mapToModel(resource)
                        ?.let { data ->
                            localDataSource.storeWeeklyWeatherForecast(data)
                            localDataSource.cacheWeeklyWeatherForecast(isCached = true)
                        }
                }
            }, shouldFetch = {
                shouldFetchFromApi(forecastRequest)
            },
            onFetchFailed = {
                flow<String> { it.message }
            }
        )
    }

    private fun shouldFetchFromApi(forecastRequest: ForecastRequest): Boolean {
        val update = when {
            localDataSource.isCurrentWeeklyWeatherForecastCached() && localDataSource.isCurrentWeeklyWeatherForecastCached() && forecastRequest.fetchFromApi.not() ->
                false

            forecastRequest.fetchFromApi ->
                true

            else ->
                true
        }
        return update
    }
}