package com.leaf.cache.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leaf.cache.database.model.Forecast
import com.leaf.cache.database.model.Weather


@ProvidedTypeConverter
class Converters {
    private val gson = Gson()
    @TypeConverter
    fun fromWeatherToJSON(weather: List<Weather>): String {
        return gson.toJson(weather).toString()
    }

    @TypeConverter
    fun toWeatherFromJSON(value: String): List<Weather> {
        val typeToken = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(value,typeToken)
    }


    @TypeConverter
    fun fromForecastListToJSON(list:List<Forecast>): String {
        return gson.toJson(list).toString()
    }

    @TypeConverter
    fun toForecastFromJSON(value: String): List<Forecast> {
        val typeToken = object : TypeToken<List<Forecast>>() {}.type
        return gson.fromJson(value,typeToken)
    }
}