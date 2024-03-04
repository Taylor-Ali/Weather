package com.leaf.data.model

import com.google.gson.annotations.SerializedName

/**
 * Wind
 * Data class used to model Wind speed and direction.
 * @param speed [Double] represents speed of wind.
 * @param deg [String] directions of wind measured in degrees.
 */
data class Wind(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val deg: Double
)