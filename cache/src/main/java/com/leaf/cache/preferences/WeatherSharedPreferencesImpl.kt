package com.leaf.cache.preferences

import android.content.SharedPreferences

class WeatherSharedPreferencesImpl(private val sharedPreferences: SharedPreferences):
    WeatherSharedPreferences {
    private val sharedPreferencesEditor = sharedPreferences.edit()

    override fun isCurrentWeatherForecastCached(): Boolean {
     return sharedPreferences
            .getBoolean(IS_CURRENT_WEATHER_REPORT_CACHED,false)
    }

    override fun cacheCurrentWeatherForecast(isCached: Boolean) {
        sharedPreferencesEditor.putBoolean(IS_CURRENT_WEATHER_REPORT_CACHED, isCached).apply()
    }

    override fun isCurrentWeeklyWeatherForecastCached(): Boolean {
        return sharedPreferences
            .getBoolean(IS_WEATHER_FORECAST_REPORT_CACHED,false)
    }

    override fun cacheWeeklyWeatherForecast(isCached: Boolean) {
        sharedPreferencesEditor.putBoolean(IS_WEATHER_FORECAST_REPORT_CACHED, isCached).apply()
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        const val IS_CURRENT_WEATHER_REPORT_CACHED = "isCurrentWeatherReportCached"
        const val IS_WEATHER_FORECAST_REPORT_CACHED = "isWeatherForecastReportCached"
    }
}