package com.leaf.cache.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leaf.cache.database.model.Cloud
import com.leaf.cache.database.model.Coord
import com.leaf.cache.database.model.Main
import com.leaf.cache.database.model.Sys
import com.leaf.cache.database.model.Weather
import com.leaf.cache.database.model.Wind

@Entity(tableName = "current_weather_forecast")
data class CurrentWeatherForecastEntity(
    @PrimaryKey(autoGenerate = false) val id: Long?,
    @Embedded val coord: Coord?,
    @Embedded val weather: Weather?,
    @ColumnInfo(name = "base") val base: String?,
    @Embedded val main: Main?,
    @ColumnInfo(name = "visibility") val visibility: Int,
    @Embedded val wind: Wind?,
    @Embedded val cloud: Cloud?,
    @ColumnInfo(name = "dateTime") val dateTime: Long,
    @Embedded val sys: Sys?,
    @ColumnInfo(name = "timeZone") val timeZone: Int,
    @ColumnInfo(name = "cod") val cod: Int?,
    @ColumnInfo(name = "city_name") val cityName: String?,
    @ColumnInfo(name = "dateTimeText") val dateTimeText: String?
)