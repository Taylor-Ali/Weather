package com.leaf.data.requests

data class WeeklyForecastRequest(
    val latitude: Double?,
    val longitude: Double?,
    val cityName: String
)
