package com.leaf.repository.model


/**
 * Main
 * Data class used to model the temperature.
 * @param temp [Double] represents the actual temperature.
 * @param feelsLike [Double] represents the temperature relative other areas and surrounds.
 * @param tempMin [Double] represents the minimum temperature.
 * @param tempMax [Double] represents the maximum temperature.
 * @param temp [Double] represents the latitude.
 * @param pressure [Double] represents the air pressure.
 * @param humidity [Double] represents the humidity in the air.
 */
data class Main(
    val temp: Double?,
    val feelsLike: Double?,
    val tempMin: Double?,
    val tempMax: Double?,
    val pressure: Double?,
    val humidity: Double?
)