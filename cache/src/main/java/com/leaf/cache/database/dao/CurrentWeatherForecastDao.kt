package com.leaf.cache.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.leaf.cache.database.entities.CurrentWeatherForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherForecastDao {

    @Query("SELECT * FROM current_weather_forecast where city_name = :cityName")
    fun getCurrentForecast(cityName: String): Flow<CurrentWeatherForecastEntity?>

    @Query("SELECT * FROM current_weather_forecast where dateTime <= :date AND city_name like :cityName")
    suspend fun getCurrentForecastByCity(
        date: Long,
        cityName: String
    ): CurrentWeatherForecastEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeCurrentForecast(currentWeatherForecastEntity: CurrentWeatherForecastEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrentForecast(currentWeatherForecastEntity: CurrentWeatherForecastEntity)

    @Delete
    suspend fun deleteCurrentForecast(currentWeatherForecastEntity: CurrentWeatherForecastEntity)
}