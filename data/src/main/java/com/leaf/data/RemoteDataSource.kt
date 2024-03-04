package com.leaf.data

import com.leaf.data.requests.CurrentForecastRequest
import com.leaf.data.requests.WeeklyForecastRequest
import com.leaf.data.response.CurrentForecastResponse
import com.leaf.data.response.WeeklyForecastResponse
import retrofit2.Response

interface RemoteDataSource {

     suspend fun fetchCurrentWeatherForecast(currentForecastRequest: CurrentForecastRequest): Response<CurrentForecastResponse>

    suspend fun fetchWeeklyForecastResponse(weeklyForecastRequest: WeeklyForecastRequest): Response<WeeklyForecastResponse>
}