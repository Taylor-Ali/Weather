package com.leaf.data.model

import com.google.gson.annotations.SerializedName

/**
 * Coord
 * Data class used to model the coordinates of the city.
 * @param lat [Double] represents the latitude.
 * @param lon [Double] represents the longitude.
 */
data class Coord(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)
