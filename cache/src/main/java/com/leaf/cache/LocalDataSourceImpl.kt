package com.leaf.cache

import com.leaf.cache.database.WeatherAppDatabase
import com.leaf.cache.database.entities.CityEntity
import com.leaf.cache.database.entities.CurrentWeatherForecastEntity
import com.leaf.cache.database.entities.WeeklyForecastEntity
import com.leaf.cache.preferences.WeatherSharedPreferences
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val database: WeatherAppDatabase,
    private val weatherSharedPreferences: WeatherSharedPreferences
) : LocalDataSource {
    override fun fetchCurrentWeatherForecast(cityName: String): Flow<CurrentWeatherForecastEntity?> {
        return database.currentForecastDao().getCurrentForecast(cityName)
    }

    override fun fetchWeeklyWeatherForecast(cityName : String): Flow<WeeklyForecastEntity?> {
        return database.weeklyWeatherForecastDao().getWeeklyForecast(cityName)
    }

    override suspend fun storeCurrentWeatherForecast(currentWeatherForecastEntity: CurrentWeatherForecastEntity) {
        database.currentForecastDao().storeCurrentForecast(currentWeatherForecastEntity)

    }

    override suspend fun storeWeeklyWeatherForecast(weeklyForecastEntity: WeeklyForecastEntity) {
        database.weeklyWeatherForecastDao().storeWeeklyWeatherForecast(weeklyForecastEntity)
    }

    override suspend fun fetchCity(cityName: String): CityEntity? {
        return database.cityDao().getCity(cityName)
    }

    override suspend fun fetchAllCities(): List<CityEntity>? {
        return database.cityDao().getAllCities()
    }

    override suspend fun storeCity(cityEntity: CityEntity) {
        database.cityDao().addCity(cityEntity)
    }

    override suspend fun deleteCity(cityEntity: CityEntity) {
        return database.cityDao().deleteCity(cityEntity)
    }

    override fun isCurrentWeatherForecastCached(): Boolean {
        return weatherSharedPreferences.isCurrentWeatherForecastCached()
    }

    override fun isCurrentWeeklyWeatherForecastCached(): Boolean {
        return weatherSharedPreferences.isCurrentWeeklyWeatherForecastCached()
    }

    override fun cacheCurrentWeatherForecast(isCached: Boolean) {
        weatherSharedPreferences.cacheCurrentWeatherForecast(isCached)
    }

    override fun cacheWeeklyWeatherForecast(isCached: Boolean) {
        weatherSharedPreferences.cacheWeeklyWeatherForecast(isCached)
    }

    override fun clearCache() {
        weatherSharedPreferences.clear()
    }
}