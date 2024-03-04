package com.leaf.repository.model

/**
 * Weather
 * Data class used to model basic weather relating to city/area information.
 * @param id [Int] represents the id of query.
 * @param main [String] represents the basic weather conditions.
 * @param description [String] represents a brief description of the weather conditions.
 * @param icon [String] icon of weather conditions.
 */
data class Weather(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?
)