package com.leaf.data.service

import com.leaf.data.response.WeeklyForecastResponse
import com.leaf.data.response.CurrentForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
// Please see https://openweathermap.org/current
    @GET("weather")
    suspend fun getCurrentWeatherForecast(
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null,
        @Query("q") cityName: String? = null,
    ): Response<CurrentForecastResponse>


    // Please see https://openweathermap.org/forecast5
    @GET("forecast")
    suspend fun getWeeklyForecastResponse(
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null,
        @Query("q") cityName: String? = null,
    ): Response<WeeklyForecastResponse>
}