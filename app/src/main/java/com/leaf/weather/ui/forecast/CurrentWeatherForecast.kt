package com.leaf.weather.ui.forecast

data class CurrentWeatherForecast(
    val cityName : String,
    val currentTemp: Int,
    val minTemp: Int,
    val maxTemp: Int,
    val weatherConditions: String,
    val weatherConditionTheme: WeatherConditionTheme,
)