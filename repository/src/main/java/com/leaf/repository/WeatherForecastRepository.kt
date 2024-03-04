package com.leaf.repository

import com.leaf.repository.model.ForecastRequest
import com.leaf.repository.model.WeatherReport
import com.leaf.repository.model.WeeklyForecastReport
import com.leaf.repository.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {

    suspend fun getCurrentWeatherReport(
        forecastRequest: ForecastRequest
    ): Flow<Resource<WeatherReport?>>

    suspend fun getWeeklyWeatherReport(
        forecastRequest: ForecastRequest
    ): Flow<Resource<WeeklyForecastReport?>>

}