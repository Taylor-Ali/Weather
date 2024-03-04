package com.leaf.data.response

import com.google.gson.annotations.SerializedName
import com.leaf.data.model.City
import com.leaf.data.response.CurrentForecastResponse

/**
 * WeeklyForecastResponse
 * Data class used to model the longer weather report.
 * @param cod [Int] represents weather api response code.
 * @param message [String] represents weather api response message.
 * @param cnt [Int] represents how much detail was requested.
 * @param list[List][CurrentForecastResponse] provides the forecast and weather based on the cnt.
 * @param list[List][CurrentForecastResponse] provides the city information.
 */
data class WeeklyForecastResponse(
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list: List<CurrentForecastResponse>,
    @SerializedName("city")
    val city: City
)