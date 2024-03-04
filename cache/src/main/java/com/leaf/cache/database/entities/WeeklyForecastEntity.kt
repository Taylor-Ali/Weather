package com.leaf.cache.database.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leaf.cache.database.model.Forecast


@Entity(
    tableName = "weekly_weather_forecast")
data class WeeklyForecastEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "cod") val cod: Int?,
    @ColumnInfo(name = "message") val message: String?,
    @ColumnInfo(name = "cnt") val cnt: Int?,
    @ColumnInfo(name = "forecast") val forecast: List<Forecast>?,
    @ColumnInfo(name = "city_name") val cityName: String?,
    @ColumnInfo(name = "dateTime") val dateTime: Long?,
)