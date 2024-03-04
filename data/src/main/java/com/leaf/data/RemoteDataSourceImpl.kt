package com.leaf.data

import com.leaf.data.requests.CurrentForecastRequest
import com.leaf.data.requests.WeeklyForecastRequest
import com.leaf.data.response.CurrentForecastResponse
import com.leaf.data.response.WeeklyForecastResponse
import com.leaf.data.service.WeatherService
import com.leaf.utils.BuildConfigProvider
import retrofit2.Response

class RemoteDataSourceImpl(
    private val weatherService: WeatherService,
) : RemoteDataSource {
    override suspend fun fetchCurrentWeatherForecast(currentForecastRequest: CurrentForecastRequest): Response<CurrentForecastResponse> {
        return weatherService.getCurrentWeatherForecast(
            lat = currentForecastRequest.latitude,
            lon = currentForecastRequest.longitude,
            cityName = currentForecastRequest.cityName,
        )
    }

    override suspend fun fetchWeeklyForecastResponse(weeklyForecastRequest: WeeklyForecastRequest): Response<WeeklyForecastResponse> {
        return weatherService.getWeeklyForecastResponse(
            lat = weeklyForecastRequest.latitude,
            lon = weeklyForecastRequest.longitude,
            cityName = weeklyForecastRequest.cityName
        )
    }
}