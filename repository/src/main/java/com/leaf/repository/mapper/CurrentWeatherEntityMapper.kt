package com.leaf.repository.mapper

import com.leaf.cache.database.entities.CurrentWeatherForecastEntity
import com.leaf.repository.model.Cloud
import com.leaf.repository.model.Coord
import com.leaf.repository.model.Main
import com.leaf.repository.model.Sys
import com.leaf.repository.model.Weather
import com.leaf.repository.model.WeatherReport
import com.leaf.repository.model.Wind

object CurrentWeatherEntityMapper : Mapper<CurrentWeatherForecastEntity, WeatherReport> {
    override fun mapFromModel(type: WeatherReport?): CurrentWeatherForecastEntity? {
        return try {
            type?.let {
                CurrentWeatherForecastEntity(
                    id = it.id,
                    coord = com.leaf.cache.database.model.Coord(
                        latitude = it.coord?.lat,
                        longitude = it.coord?.lon
                    ),
                    weather = it.weather?.component1().let { weather ->
                        com.leaf.cache.database.model.Weather(
                            weather?.main,
                            weather?.description,
                            weather?.icon
                        )
                    },
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
                    cityName = it.name,
                    cod = it.cod,
                    dateTimeText = it.dateTimeText

                )
            }
        } catch (e: Exception) {
            null
        }

    }

    override fun mapToModel(type: CurrentWeatherForecastEntity?): WeatherReport? {
        return try {
            type?.let {
                WeatherReport(
                    id = it.id ?: 0,
                    coord = Coord(it.coord?.latitude, it.coord?.longitude),
                    weather = listOf(
                        Weather(
                            id = null,
                            main = it.weather?.main,
                            icon = it.weather?.icon,
                            description = it.weather?.description
                        )
                    ),

                    base = it.base.orEmpty(),
                    main = Main(
                        temp = it.main?.temperature,
                        tempMax = it.main?.temperatureMax,
                        tempMin = it.main?.temperatureMin,
                        pressure = it.main?.pressure,
                        humidity = it.main?.humidity,
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
                    name = it.cityName.orEmpty(),
                    cod = it.cod ?: 0,
                    dateTimeText = it.dateTimeText

                )
            }
        } catch (e: Exception) {
            null
        }
    }
}