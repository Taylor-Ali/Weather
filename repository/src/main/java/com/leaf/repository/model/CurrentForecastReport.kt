package com.leaf.repository.model


/**
 * ForecastResponse
 * Data class used to model the longer weather report.
 * @param cod [Int] represents weather api response code.
 * @param message [String] represents weather api response message.
 * @param cnt [Int] represents how much detail was requested.
 * @param list[List][Weather] provides the forecast and weather based on the cnt.
 * @param city[City][Weather] provides the city information.
 */
data class CurrentForecastReport(
    val cod: Int?,
    val message: String?,
    val cnt: Int?,
    val list: List<Weather>?,
    val city: City?
)