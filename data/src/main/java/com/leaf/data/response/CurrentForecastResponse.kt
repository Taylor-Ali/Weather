package com.leaf.data.response

import com.google.gson.annotations.SerializedName
import com.leaf.data.model.Cloud
import com.leaf.data.model.Coord
import com.leaf.data.model.Main
import com.leaf.data.model.Sys
import com.leaf.data.model.Weather
import com.leaf.data.model.Wind

/**
 * CurrentForecastResponse
 * Data class used to model the weather response.
 * @param id [Double] represents id of weather report.
 * @param coord [Coord] represents latlong coordinates.
 * @param weather [Weather] provides a basic weather report.
 * @param base [String]
 * @param main [Main] represents temperature report.
 * @param visibility [Int] represents sky visibility.
 * @param wind [Wind] represents wind information.
 * @param cloud [Cloud] represents cloud coverage.
 * @param dateTime [Long] represents unix date of report.
 * @param sys [Sys] represents city/area information. .
 * @param timeZone [Int] represents unix timezone offset.
 * @param name [String] represents name of area/city.
 * @param cod [Int] represents weather api response code.
 * @param dateTimeText [String] represents date time in text.
 */
data class CurrentForecastResponse(
    @SerializedName("id")
    val id : Long,
    @SerializedName("coord")
    val coord: Coord?,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("base")
    val base : String?,
    @SerializedName("main")
    val main: Main,
    @SerializedName("visibility")
    val visibility : Int,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("clouds")
    val cloud: Cloud,
    @SerializedName("dt")
    val dateTime: Long,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timeZone: Int,
    @SerializedName("name")
    val name : String?,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("dt_txt")
    val dateTimeText :String
)