package com.leaf.data.requests

data class CurrentForecastRequest(
    val latitude: Double?,
    val longitude: Double?,
    val cityName: String,
)

