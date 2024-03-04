package com.leaf.cache

import com.leaf.cache.database.entities.CityEntity
import com.leaf.cache.database.entities.CurrentWeatherForecastEntity
import com.leaf.cache.database.entities.WeeklyForecastEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun fetchCurrentWeatherForecast(cityName: String): Flow<CurrentWeatherForecastEntity?>

    fun fetchWeeklyWeatherForecast(cityName: String): Flow<WeeklyForecastEntity?>

    suspend fun storeCurrentWeatherForecast(currentWeatherForecastEntity: CurrentWeatherForecastEntity)

    suspend fun storeWeeklyWeatherForecast(weeklyForecastEntity: WeeklyForecastEntity)

    suspend fun fetchCity(cityName: String): CityEntity?

    suspend fun fetchAllCities(): List<CityEntity>?

    suspend fun storeCity(cityEntity: CityEntity)

    suspend fun deleteCity(cityEntity: CityEntity)

    fun isCurrentWeatherForecastCached(): Boolean

    fun isCurrentWeeklyWeatherForecastCached(): Boolean

    fun cacheCurrentWeatherForecast(isCached: Boolean)

    fun cacheWeeklyWeatherForecast(isCached: Boolean)

    fun clearCache()

}