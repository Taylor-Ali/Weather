package com.leaf.cache.database.model

data class Forecast(
    val id: Long,
    val coord: Coord?,
    val weather: List<Weather>,
    val base: String?,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val cloud: Cloud,
    val dateTime: Long,
    val sys: Sys,
    val timeZone: Int,
    val name: String?,
    val cod: Int,
    val dateTimeText: String
)

