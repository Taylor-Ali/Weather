package com.leaf.repository.mapper

import com.leaf.data.response.CurrentForecastResponse
import com.leaf.repository.model.Cloud
import com.leaf.repository.model.Coord
import com.leaf.repository.model.Main
import com.leaf.repository.model.Sys
import com.leaf.repository.model.Weather
import com.leaf.repository.model.WeatherReport
import com.leaf.repository.model.Wind

object CurrentWeatherForecastResponseMapper : Mapper<CurrentForecastResponse, WeatherReport> {
    override fun mapFromModel(type: WeatherReport?): CurrentForecastResponse? {
        return null
    }

    override fun mapToModel(type: CurrentForecastResponse?): WeatherReport? {
        return try {
            type?.let { currentForecastResponse ->
                WeatherReport(
                    id = currentForecastResponse.id,
                    coord = Coord(
                        lat = currentForecastResponse.coord?.lat ?: 0.0,
                        lon = currentForecastResponse.coord?.lon ?: 0.0
                    ),
                    weather = currentForecastResponse.weather.map {
                        Weather(
                            id = it.id ?: 0,
                            main = it.main,
                            description = it.description,
                            icon = it.icon
                        )
                    },
                    base = currentForecastResponse.base.orEmpty(),
                    main = Main(
                        temp = currentForecastResponse.main.temp,
                        tempMax = currentForecastResponse.main.tempMax,
                        tempMin = currentForecastResponse.main.tempMin,
                        pressure = currentForecastResponse.main.pressure,
                        humidity = currentForecastResponse.main.humidity,
                        feelsLike = currentForecastResponse.main.feelsLike
                    ),
                    visibility = currentForecastResponse.visibility,
                    wind = Wind(
                        speed = currentForecastResponse.wind.speed,
                        deg = currentForecastResponse.wind.deg
                    ),
                    cloud = Cloud(all = currentForecastResponse.cloud.all),
                    dateTime = currentForecastResponse.dateTime,
                    sys = Sys(
                        id = currentForecastResponse.sys.id,
                        country = currentForecastResponse.sys.country.orEmpty(),
                        sunrise = currentForecastResponse.sys.sunrise,
                        sunset = currentForecastResponse.sys.sunset,
                        type = currentForecastResponse.sys.type
                    ),
                    timeZone = currentForecastResponse.timeZone,
                    name = currentForecastResponse.name.orEmpty(),
                    cod = currentForecastResponse.cod,
                    dateTimeText = currentForecastResponse.dateTimeText

                )
            }

        } catch (e: Exception) {
            null

        }
    }
}
