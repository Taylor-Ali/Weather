package com.leaf.weather.ui.forecast
data class WeatherForecastReport(
    val dayOfWeek: String,
    val temp: Int,
    val icon : Int,
    val measurement : Int
)