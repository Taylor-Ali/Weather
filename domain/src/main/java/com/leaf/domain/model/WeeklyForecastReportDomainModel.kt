package com.leaf.domain.model


data class WeeklyForecastReportDomainModel(
    val dayOfWeek: String,
    val temperature: Int,
    val weatherCondition : WeatherCondition,
    val timeOfForecast : Long

)