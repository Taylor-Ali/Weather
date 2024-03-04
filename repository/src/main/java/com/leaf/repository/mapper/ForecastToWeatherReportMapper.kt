package com.leaf.repository.mapper

import com.leaf.cache.database.model.Forecast
import com.leaf.repository.model.Cloud
import com.leaf.repository.model.Coord
import com.leaf.repository.model.Main
import com.leaf.repository.model.Sys
import com.leaf.repository.model.Weather
import com.leaf.repository.model.WeatherReport
import com.leaf.repository.model.Wind

object ForecastToWeatherReportMapper : Mapper<Forecast, WeatherReport> {

    override fun mapFromModel(type: WeatherReport?): Forecast? {
        return try {
            type?.let {
                Forecast(
                    id = it.id ?: 0,
                    coord = com.leaf.cache.database.model.Coord(
                        latitude = it.coord?.lat,
                        longitude = it.coord?.lon
                    ),
                    weather = it.weather?.map { weather ->
                         com.leaf.cache.database.model.Weather(
                            main = weather.main,
                            description = weather.description,
                            icon = weather.icon
                        )
                    }.orEmpty(),
                    base = it.base,
                    main = com.leaf.cache.database.model.Main(
                        temperature = it.main?.temp,
                        temperatureMax = it.main?.tempMax,
                        temperatureMin = it.main?.tempMin,
                        pressure = it.main?.pressure,
                        humidity = it.main?.humidity,
                        feelsLike = it.main?.feelsLike
                    ),
                    visibility = it.visibility ?: 0,
                    wind = com.leaf.cache.database.model.Wind(
                        speed = it.wind?.speed,
                        degrees = it.wind?.deg
                    ),
                    cloud = com.leaf.cache.database.model.Cloud(all = it.cloud?.all),
                    dateTime = it.dateTime ?: 0,
                    sys = com.leaf.cache.database.model.Sys(
                        country = it.sys?.country,
                        sunrise = it.sys?.sunrise,
                        sunset = it.sys?.sunset,
                        type = it.sys?.type
                    ),
                    timeZone = it.timeZone ?: 0,
                    name = it.name,
                    cod = it.cod ?: 0,
                    dateTimeText = it.dateTimeText ?: ""

                )
            }
        } catch (e: Exception) {
            null
        }
    }

    override fun mapToModel(type: Forecast?): WeatherReport? {
        return try {
            type?.let {
                WeatherReport(
                    id = it.id,
                    coord = Coord(it.coord?.latitude, it.coord?.longitude),
                    weather = it.weather.map { weather ->
                        Weather(
                            id = null,
                            main = weather.main,
                            description = weather.description,
                            icon = weather.icon
                        )
                    },

                    base = it.base.orEmpty(),
                    main = Main(
                        temp = it.main?.temperature,
                        tempMax = it.main?.temperatureMax,
                        tempMin = it.main.temperatureMin,
                        pressure = it.main.pressure,
                        humidity = it.main.humidity,
                        feelsLike = it.main?.feelsLike
                    ),
                    visibility = it.visibility,
                    wind = Wind(speed = it.wind?.speed, deg = it.wind?.degrees),
                    cloud = Cloud(all = it.cloud?.all),
                    dateTime = it.dateTime,
                    sys = Sys(
                        id = 0,
                        country = it.sys?.country.orEmpty(),
                        sunrise = it.sys?.sunrise ?: 0,
                        sunset = it.sys?.sunset ?: 0,
                        type = it.sys?.type ?: 0
                    ),
                    timeZone = it.timeZone,
                    name = it.name.orEmpty(),
                    cod = it.cod,
                    dateTimeText = it.dateTimeText

                )
            }
        } catch (e: Exception) {
            null
        }
    }
}