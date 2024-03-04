package com.leaf.data.model

import com.google.gson.annotations.SerializedName


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
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("humidity")
    val humidity: Double
)