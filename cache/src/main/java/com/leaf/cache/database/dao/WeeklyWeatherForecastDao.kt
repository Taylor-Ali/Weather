package com.leaf.cache.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.leaf.cache.database.entities.WeeklyForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeeklyWeatherForecastDao {
    @Query("SELECT * FROM weekly_weather_forecast where city_name = :cityName")
    fun getWeeklyForecast(cityName: String): Flow<WeeklyForecastEntity?>

    @Query("SELECT * FROM weekly_weather_forecast where dateTime like :date AND city_name like :cityId")
    fun getWeatherForecastReportByCity(date: String, cityId: Long): Flow<WeeklyForecastEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeWeeklyWeatherForecast(weatherEntity: WeeklyForecastEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWeeklyWeatherForecast(weatherEntity: WeeklyForecastEntity)

    @Delete
    suspend fun deleteWeeklyWeatherForecast(weatherEntity: WeeklyForecastEntity)
}