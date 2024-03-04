package com.leaf.domain.model

data class WeatherRequest(
    val latitude: Double?,
    val longitude: Double?,
    val cityName: String?,
    val dateTime: Long?,
    val fetchFromApi: Boolean
)
