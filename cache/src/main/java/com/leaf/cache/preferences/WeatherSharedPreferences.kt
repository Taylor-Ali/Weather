package com.leaf.cache.preferences

interface WeatherSharedPreferences {

    fun isCurrentWeatherForecastCached() : Boolean

    fun isCurrentWeeklyWeatherForecastCached() : Boolean

    fun cacheCurrentWeatherForecast(isCached: Boolean)

    fun cacheWeeklyWeatherForecast(isCached: Boolean)

    fun clear()

}