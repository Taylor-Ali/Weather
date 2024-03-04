package com.leaf.data.model

import com.google.gson.annotations.SerializedName

/**
 * Sys
 * Data class used to model city/area information.
 * @param id [Int] represents the id of query.
 * @param type [Int].
 * @param country [String] represents the country queried.
 * @param sunrise [Long] unix time of sunrise.
 * @param sunset [Long] unix time of sunset
 */
data class Sys(
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: Int,
    @SerializedName("country")
    val country: String?,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long
)