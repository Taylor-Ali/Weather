package com.leaf.repository.model


/**
 * Wind
 * Data class used to model Wind speed and direction.
 * @param speed [Double] represents speed of wind.
 * @param deg [String] directions of wind measured in degrees.
 */
data class Wind(
    val speed: Double?,
    val deg: Double?
)