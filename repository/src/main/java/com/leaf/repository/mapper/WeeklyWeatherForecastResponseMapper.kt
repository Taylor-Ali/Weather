package com.leaf.repository.mapper

import com.leaf.data.response.WeeklyForecastResponse
import com.leaf.repository.model.City
import com.leaf.repository.model.Coord
import com.leaf.repository.model.WeeklyForecastReport

object WeeklyWeatherForecastResponseMapper : Mapper<WeeklyForecastResponse, WeeklyForecastReport> {
    override fun mapFromModel(type: WeeklyForecastReport?): WeeklyForecastResponse? {
       return null
    }

    override fun mapToModel(type: WeeklyForecastResponse?): WeeklyForecastReport? {
        return try {
            type?.let {
                WeeklyForecastReport(
                    id = null,
                    cod = it.cod,
                    message = it.message,
                    cnt = it.cnt,
                    list = it.list.map {currentForecastResponse->
                        CurrentWeatherForecastResponseMapper.mapToModel(currentForecastResponse)
                    },
                    city = City(
                        id = it.city.id,
                        name = it.city.name,
                        coord = Coord(lat = it.city.coord.lat, lon = it.city.coord.lon),
                        country = it.city.country,
                        sunset = it.city.sunset,
                        sunrise = it.city.sunrise,
                        timezone = it.city.timezone
                    )
                )
            }
        } catch (e: Exception) {
            e.message
            return null
        }
    }
}