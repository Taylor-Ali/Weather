package com.leaf.repository.model


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
    val id: Int?,
    val type: Int?,
    val country: String?,
    val sunrise: Long?,
    val sunset: Long?
)