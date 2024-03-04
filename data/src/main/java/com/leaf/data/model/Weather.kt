package com.leaf.data.model

import com.google.gson.annotations.SerializedName

/**
 * Weather
 * Data class used to model basic weather relating to city/area information.
 * @param id [Int] represents the id of query.
 * @param main [String] represents the basic weather conditions.
 * @param description [String] represents a brief description of the weather conditions.
 * @param icon [String] icon of weather conditions.
 */
data class Weather(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)