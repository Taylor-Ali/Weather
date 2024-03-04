package com.leaf.data.model

import com.google.gson.annotations.SerializedName


/**
 * City
 * Data class used to model the city data from the weather response.
 * @param id [Long] represents the initial rate the base currency was at.
 * @param name[String] represents name of city.
 * @param coord [Coord] represents latlong coordinates.
 * @param country [String] represents the country.
 * @param timezone[Long] represents timezone offset in milliseconds.
 * @param sunrise [Long] represents unix time of sunrise.
 * @param sunset [Long] represents unix time of sunset.
 */
data class City(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("country")
    val country: String,
    @SerializedName("timezone")
    val timezone: Long,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long
)