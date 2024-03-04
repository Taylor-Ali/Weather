package com.leaf.domain.model

data class CurrentWeatherReportDomainModel(
    val name : String,
    val currentTemperature: Int,
    val minimumTemperature : Int,
    val maximumTemperature: Int,
    val conditionsDescription: String,
    val weatherCondition : WeatherCondition
)