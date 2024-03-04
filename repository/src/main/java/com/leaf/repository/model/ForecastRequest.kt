package com.leaf.repository.model

data class ForecastRequest(
    val latitude: Double?,
    val longitude: Double?,
    val cityName: String?,
    val dateTime: Long?,
    val fetchFromApi: Boolean
)