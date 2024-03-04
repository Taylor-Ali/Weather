package com.leaf.repository.model

/**
 * WeatherReport
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
 * @param dateTimeText [Long] represents date time in text.
 */
data class WeatherReport(
    val id: Long?,
    val coord: Coord?,
    val weather: List<Weather>?,
    val base: String?,
    val main: Main?,
    val visibility: Int?,
    val wind: Wind?,
    val cloud: Cloud?,
    val dateTime: Long?,
    val sys: Sys?,
    val timeZone: Int?,
    val name: String?,
    val cod: Int?,
    val dateTimeText: String?
)